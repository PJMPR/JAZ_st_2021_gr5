package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Film {
    private int filmId;
    private String title;
    private int releaseYear;
    private int rentalDuration;
    private BigDecimal rentalRate;
    private BigDecimal replacementCost;
    private Timestamp lastUpdate;
    private Language language;

    @Id
    @Column(name = "film_id")
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "release_year")
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Basic
    @Column(name = "rental_duration")
    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    @Basic
    @Column(name = "rental_rate")
    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    @Basic
    @Column(name = "replacement_cost")
    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    @Basic
    @Column(name = "last_update")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (filmId != film.filmId) return false;
        if (releaseYear != film.releaseYear) return false;
        if (rentalDuration != film.rentalDuration) return false;
        if (title != null ? !title.equals(film.title) : film.title != null) return false;
        if (rentalRate != null ? !rentalRate.equals(film.rentalRate) : film.rentalRate != null) return false;
        if (replacementCost != null ? !replacementCost.equals(film.replacementCost) : film.replacementCost != null)
            return false;
        if (lastUpdate != null ? !lastUpdate.equals(film.lastUpdate) : film.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = filmId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + releaseYear;
        result = 31 * result + rentalDuration;
        result = 31 * result + (rentalRate != null ? rentalRate.hashCode() : 0);
        result = 31 * result + (replacementCost != null ? replacementCost.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id", nullable = false)
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
