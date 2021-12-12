package com.example.demo.contracts;

import java.math.BigDecimal;

public interface FilmProjection {
    int getId();

    String getTitle();

    LanguageProjection getLanguage();

    int getReleaseYear();

    int getRentalDuration();

    BigDecimal getRentalRate();

    BigDecimal getReplacementCosts();
}
