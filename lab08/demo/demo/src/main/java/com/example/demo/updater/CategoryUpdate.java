package com.example.demo.updater;

import com.example.demo.contract.GenreDto;
import com.example.demo.contract.MovieDto;
import com.example.demo.contract.OMDbDto;
import com.example.demo.model.Category;
import com.example.demo.model.FilmCategory;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.repositories.FilmCategoryRepo;
import com.example.demo.repositories.FilmRepo;
import com.example.demo.repositories.projections.ICategoryName;
import com.example.demo.repositories.projections.IFilm;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryUpdate implements Chain {
    private final Logger logger = Logger.getLogger(Category.class.getName());

    private Chain nextInChain;
    private CategoryRepo repo;
    private FilmCategoryRepo catRepo;
    private FilmRepo filmRepo;

    public CategoryUpdate(CategoryRepo repo, FilmCategoryRepo catRepo, FilmRepo filmRepo) {
        this.repo = repo;
        this.catRepo = catRepo;
        this.filmRepo = filmRepo;
    }

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void query(MovieDto movieDto, OMDbDto omDbDto) {
        List<String> filmTitle = filmRepo.getAllFilms().stream().map(IFilm::getTitle).collect(Collectors.toList());
        for (GenreDto genre : movieDto.getGenres()) {
            List<String> dbList = repo.getAllCategories().stream().map(ICategoryName::getName).collect(Collectors.toList());
            long time = new Date().getTime();

            if (!dbList.contains(genre.getName())) {
                Category c = new Category();
                c.setCategoryId(dbList.size() + 1);
                c.setName(genre.getName());
                c.setLastUpdate(new Timestamp(time));

                repo.save(c);
                logger.log(Level.INFO, "Found and added new category: " + genre.getName());
                dbList = repo.getAllCategories().stream().map(ICategoryName::getName).collect(Collectors.toList());
            }
            FilmCategory filmCategory = new FilmCategory();
            filmCategory.setFilmId(filmTitle.indexOf(movieDto.getTitle()) + 1);
            filmCategory.setCategoryId(dbList.indexOf(genre.getName()) + 1);
            filmCategory.setLastUpdate(new Timestamp(time));

            catRepo.save(filmCategory);
        }
        nextInChain.query(movieDto, omDbDto);
    }
}
