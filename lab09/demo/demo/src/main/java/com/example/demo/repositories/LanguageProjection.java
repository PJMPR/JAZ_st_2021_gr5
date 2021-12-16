package com.example.demo.repositories;

import com.example.demo.model.Language;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

public interface LanguageProjection {
    int getId();

    Timestamp getLastUpdate();

    String getName();

    Collection<FilmInfo> getFilmsByLanguageId();

    interface FilmInfo {
        int getId();

        Language getLanguage();

        Timestamp getLastUpdate();

        int getReleaseYear();

        int getRentalDuration();

        BigDecimal getRentalRate();

        BigDecimal getReplacementCosts();

        String getTitle();
    }
}
