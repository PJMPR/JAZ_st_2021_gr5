package com.example.demo.repositories;

import com.example.demo.model.Film;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

public interface FilmProjection {
    int getId();

    Timestamp getLastUpdate();

    int getReleaseYear();

    int getRentalDuration();

    BigDecimal getRentalRate();

    BigDecimal getReplacementCosts();

    String getTitle();

    LanguageInfo getLanguage();

    interface LanguageInfo {
        int getId();

        Collection<Film> getFilmsByLanguageId();

        Timestamp getLastUpdate();

        String getName();
    }
}
