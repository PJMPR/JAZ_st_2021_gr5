package com.example.demo.repositories;

import com.example.demo.contracts.FilmDto;
import com.example.demo.model.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FilmsRepository {

    private final EntityManager entityManager;

    public List<Film> getFilmsByPage(int page, int size){

        return entityManager.createQuery("" +
                "SELECT film FROM Film film", Film.class)
                .setFirstResult((page-1)*size)
                .setMaxResults(size)
                .getResultList();
    }

    @Transactional
    public void deleteFilmById(int id) {
        entityManager.joinTransaction();
        entityManager.createQuery(""+"DELETE From Film WHERE filmId="+id).executeUpdate();
    }

    @Transactional
    public void createFilm(FilmDto newFilm) {
        Calendar timestamp = Calendar.getInstance();

        entityManager.joinTransaction();
        entityManager.createNativeQuery("INSERT INTO Film" +
                "(title,release_year,rental_duration,rental_rate,replacement_cost,last_update,language_id)" +
                "VALUES (?,?,?,?,?,?,?)")
                .setParameter(1,newFilm.getTitle())
                .setParameter(2,newFilm.getReleaseYear())
                .setParameter(3,newFilm.getRentalDuration())
                .setParameter(4,newFilm.getRentalRate())
                .setParameter(5,newFilm.getReplacementCosts())
                .setParameter(6,Timestamp.from(timestamp.getTime().toInstant()))
                .setParameter(7,newFilm.getLanguage().getId()).executeUpdate();
    }

    @Transactional
    public void updateFilm(int id, FilmDto updateFilm) {
        Calendar timestamp = Calendar.getInstance();

        entityManager.joinTransaction();
        entityManager.createQuery("UPDATE film SET" + id +
                        "(title,release_year,rental_duration,rental_rate,replacement_cost,last_update,language_id)" +
                        "VALUES (?,?,?,?,?,?,?)")
                .setParameter(1,updateFilm.getTitle())
                .setParameter(2,updateFilm.getReleaseYear())
                .setParameter(3,updateFilm.getRentalDuration())
                .setParameter(4,updateFilm.getRentalRate())
                .setParameter(5,updateFilm.getReplacementCosts())
                .setParameter(6,Timestamp.from(timestamp.getTime().toInstant()))
                .setParameter(7,updateFilm.getLanguage().getId()).executeUpdate();
    }
}
