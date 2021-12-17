package com.example.demo.repositories;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.model.Film;
import com.example.demo.model.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FilmsRepository {

    private final EntityManager entityManager;

    public List<Film> getFilmsByPage(int page, int size) {

        var films = entityManager.createQuery("" +
                        "SELECT film FROM Film film", Film.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
        return films;

    }

    public List<FilmDto> getAllFilms(int i, Language language, Integer release_year, Integer id, String title, Integer rental_duration, BigDecimal rental_rate, BigDecimal replacement_costs) {
        List<FilmDto> films = entityManager.createQuery("select film from Film film", Film.class)
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
        return films;
    }

    public void addFilm(Film film) {
        Query q = entityManager.createNativeQuery("INSERT into Film(title,release_year,rental_dutation,language_id) VALUES (?,?,?,?,?)");
        q.setParameter(1, film.getTitle());
        q.setParameter(2, film.getReleaseYear());
        q.setParameter(3, film.getRentalDuration());
        q.setParameter(4, film.getRentalRate());
        q.setParameter(5, film.getLanguage().getLanguageId());

        q.executeUpdate();

    }

    public void deleteFilm(int id) {
        Query q = entityManager.createQuery("delete  from Film film WHERE Film.filmId =" + id, Film.class);
        q.executeUpdate();
    }


}
