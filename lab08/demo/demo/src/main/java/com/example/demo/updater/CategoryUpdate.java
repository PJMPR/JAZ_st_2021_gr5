package com.example.demo.updater;

import com.example.demo.contract.GenreDto;
import com.example.demo.contract.MovieDto;
import com.example.demo.contract.OMDbDto;
import com.example.demo.model.Category;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.repositories.projections.ICategoryName;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryUpdate implements Chain {
    private Chain nextInChain;
    private CategoryRepo repo;

    public CategoryUpdate(CategoryRepo repo) {
        this.repo = repo;
    }

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void query(MovieDto movieDto, OMDbDto omDbDto) {
        for (GenreDto genre : movieDto.getGenres()) {
            List<String> dbList = repo.getAllCategories().stream().map(ICategoryName::getName).collect(Collectors.toList());

            if (!dbList.contains(genre.getName())) {
                long time = new Date().getTime();

                Category c = new Category();
                c.setCategoryId(dbList.size() + 1);
                c.setName(genre.getName());
                c.setLastUpdate(new Timestamp(time));

                repo.save(c);
            }
            nextInChain.query(movieDto, omDbDto);
        }
    }
}
