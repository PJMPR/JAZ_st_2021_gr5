package com.example.demo.updaters;

import com.example.demo.contract.MovieFromOmdb;
import com.example.demo.model.Category;
import com.example.demo.model.Film;
import com.example.demo.model.FilmCategory;
import com.example.demo.repositories.CategoryInsertRepo;
import com.example.demo.repositories.FilmCategoryInsertRepo;
import com.example.demo.services.OmdbService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class updateCategories {
    private List<String> categories;
    private FilmCategoryInsertRepo categoryInsertRepo;
    private OmdbService omdbService;
    private String title;
    private String categoryName;

    public void updateCategories(Collection<Film> films) {

        for (Film film: films) {
            title = film.getTitle();
            MovieFromOmdb movie = omdbService.getMovie(title);
            categories = Arrays.asList(movie.getCategory().split(","));

            for (FilmCategory filmCategory : film.getFilmCategories()) {
                Category category = filmCategory.getCategory();
                categoryName = category.getName();

                if (categories.contains(categoryName)) {
                    categories.remove(categoryName);
                }
            }

            if (!categoryName.isEmpty()) {
                for (String stringCategory : categories) {
                    Category category = new Category();
                    category.setName(stringCategory);
                    categoryInsertRepo.insertCategory(category);
                    categoryInsertRepo.insertFilmCategory(category, film);
                }
            }
        }
    }

}
