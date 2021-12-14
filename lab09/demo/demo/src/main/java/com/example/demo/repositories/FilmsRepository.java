package com.example.demo.repositories;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.model.Film;
import com.example.demo.model.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FilmsRepository {

    private final EntityManager entityManager;

    public List<FilmDto> getAllFilms(){
        return entityManager.createQuery("select film from Film film",Film.class)
                .getResultList()
                .stream()
                .map(film -> new FilmDto(film.getFilmId(),
                        film.getTitle(),
                        film.getReleaseYear(),
                        new LanguageDto(film.getLanguage().getLanguageId(), film.getLanguage().getName()),
                        new BigDecimal(film.getRentalDuration()),
                        film.getRentalRate(),
                        film.getReplacementCost()))
                .collect(Collectors.toList());
    }

    public List<FilmDto> getFilmsByPage(int page, int size) {

        return entityManager.createQuery("" +
                        "SELECT film FROM Film film", Film.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList()
                .stream()
                .map(film -> new FilmDto(film.getFilmId(),
                        film.getTitle(),
                        film.getReleaseYear(),
                        new LanguageDto(film.getLanguage().getLanguageId(), film.getLanguage().getName()),
                        new BigDecimal(film.getRentalDuration()),
                        film.getRentalRate(),
                        film.getReplacementCost())).collect(Collectors.toList());
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


    /*
        id jest generowane automatycznie, dlatego nie uwzglednialem go przy query
        Front przesyła id zawsze ustawione na 0 obojętnie co wpiszemy na stronie
     */
    @Transactional
    public void createFilm(FilmDto newFilm){
        Calendar timestamp = Calendar.getInstance();

        entityManager.joinTransaction();
        entityManager.createNativeQuery("INSERT INTO Film " +
                "(title,release_year,rental_duration,rental_rate,replacement_cost,last_update,language_id)" +
                "VALUES (?,?,?,?,?,?,?)")
                .setParameter(1,newFilm.getTitle())
                .setParameter(2,newFilm.getReleaseYear())
                .setParameter(3,newFilm.getRentalDuration())
                .setParameter(4,newFilm.getRentalRate())
                .setParameter(5,newFilm.getReplacementCosts())
                .setParameter(6, Timestamp.from(timestamp.getTime().toInstant()))
                .setParameter(7,newFilm.getLanguage().getId())
                .executeUpdate();
    }

    @Transactional
    public void deleteFilmById(int id) {

        entityManager.joinTransaction();
        entityManager.createQuery("DELETE from Film f where f.filmId=:id").setParameter("id", id).executeUpdate();
    }
}
