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

    public List<Film> getFilmsByPage(int page, int size){

        var films =entityManager.createQuery("" +
                "SELECT film FROM Film film")
                .setFirstResult((page-1)*size)
                .setMaxResults(size)
                .getResultList();
        return films;
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
}
