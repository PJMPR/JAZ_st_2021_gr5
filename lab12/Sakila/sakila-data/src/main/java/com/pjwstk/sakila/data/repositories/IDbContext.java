package com.pjwstk.sakila.data.repositories;

import org.springframework.stereotype.Repository;

public interface IDbContext {
    ActorsRepository getActors();

    FilmRepository getFilms();

    CategoryRepository getCategories();

    LanguageRepository getLanguages();

    FilmCategoryRepository getFilmCategories();

    FilmActorRepository getFilmActors();

    CustomersRepository getCustomers();
}
