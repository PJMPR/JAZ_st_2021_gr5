package com.example.demo.repositories;

import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FilmsRepository {

    private final EntityManager entityManager;

    public List<Film> getFilmsByPage(int page, int size) {

        return entityManager.createQuery("" +
                        "SELECT film FROM Film film", Film.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
/*
       return entityManager.createNativeQuery("" +
                       "SELECT * " +
                       "FROM film " +
                       "limit " +
                       size +
                       " OFFSET "
                       +
                       (page-1)*size, Film.class)
                .getResultList()
               ;
               *
 */
    }

    public void deleteFilmById(int id) {
        entityManager.createQuery("DELETE from Film where filmId=:id").setParameter("id", id).executeUpdate();
    }
}
