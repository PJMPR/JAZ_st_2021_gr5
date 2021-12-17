package com.example.demo.contracts;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmDto {
    private Integer id;
    private String title;
    private Integer releaseYear;
    private LanguageDto language;
    private BigDecimal rentalDuration;
    private BigDecimal rentalRate;
    private BigDecimal replacementCosts;

}
