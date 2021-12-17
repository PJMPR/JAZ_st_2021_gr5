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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setLanguage(LanguageDto language) {
        this.language = language;
    }

    public void setRentalDuration(BigDecimal rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public void setReplacementCosts(BigDecimal replacementCosts) {
        this.replacementCosts = replacementCosts;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public LanguageDto getLanguage() {
        return language;
    }

    public BigDecimal getRentalDuration() {
        return rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public BigDecimal getReplacementCosts() {
        return replacementCosts;
    }
}
