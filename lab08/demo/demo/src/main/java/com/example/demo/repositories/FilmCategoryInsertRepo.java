package com.example.demo.repositories;

import com.example.demo.model.Category;
import com.example.demo.model.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class FilmCategoryInsertRepo {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void insertFilmCategory(Category category, Film film) {
        entityManager.createNativeQuery("INSERT INTO film_category(film_id, category_id) VALUES (?,?)")
                .setParameter(1, film.getFilmId())
                .setParameter(2, category.getCategoryId())
                .executeUpdate();
    }

    @Transactional
    public void insertCategory(Category category) {
        entityManager.createNativeQuery("INSERT INTO category(name) VALUES (?)")
                .setParameter(1, category.getName())
                .executeUpdate();
    }
}
