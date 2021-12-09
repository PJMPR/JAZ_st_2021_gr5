package com.example.demo.repositories;

import com.example.demo.model.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class InsertFilmRepo {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void deleteFilm(Film film) {
        entityManager.createNativeQuery("DELETE FROM film WHERE film.film_id=?")
                .setParameter(1, film.getFilmId())
                .executeUpdate();
    }
}
