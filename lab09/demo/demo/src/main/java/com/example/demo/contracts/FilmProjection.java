package com.example.demo.contracts;

public interface FilmProjection {
    int getId();

    String getTitle();

    LanguageProjection getLanguage();

    int getReleaseYear();

    int getRentalDuration();

    int getRentalRate();

    int getReplacementCosts();
}
