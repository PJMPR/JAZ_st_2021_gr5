package com.pjwstk.sakila.data.repositories;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Getter
@Repository
public class DbContext implements IDbContext {

    private final ActorsRepository actors;
    private final FilmRepository films;
    private final CategoryRepository categories;
    private final LanguageRepository languages;
    private final FilmCategoryRepository filmCategories;
    private final FilmActorRepository filmActors;
    private final CustomersRepository customers;

}
