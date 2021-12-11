package com.example.demo.contracts;

import org.springframework.beans.factory.annotation.Value;

public interface FilmProjection {
    @Value("#{target.filmId}")
    int getId();

    String getTitle();

    LanguageProjection getLanguage();

    int getReleaseYear();

    int getRentalDuration();

    int getRentalRate();

    @Value("#{target.replacementCost}")
    int getReplacementCosts();
}
