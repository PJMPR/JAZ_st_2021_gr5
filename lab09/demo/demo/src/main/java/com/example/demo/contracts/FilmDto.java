package com.example.demo.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {
    private Integer id;
    private String title;
    private Integer releaseYear;
    private LanguageDto language;
    private BigDecimal rentalDuration;
    private BigDecimal rentalRate;
    private BigDecimal replacementCosts;
}
