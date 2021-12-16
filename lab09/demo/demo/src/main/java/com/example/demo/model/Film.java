package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Film {
    private int id;
    private String title;
    private int releaseYear;
    private int rentalDuration;
    private BigDecimal rentalRate;
    private BigDecimal replacementCosts;
    private Timestamp lastUpdate;
    private Language language;

    @Id
    @Column(name = "film_id")
    public int getId() {
        return id;
    }

    public void setId(int filmId) {
        this.id = filmId;
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
    public BigDecimal getReplacementCosts() {
        return replacementCosts;
    }

    public void setReplacementCosts(BigDecimal replacementCost) {
        this.replacementCosts = replacementCost;
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

        if (id != film.id) return false;
        if (releaseYear != film.releaseYear) return false;
        if (rentalDuration != film.rentalDuration) return false;
        if (!Objects.equals(title, film.title)) return false;
        if (!Objects.equals(rentalRate, film.rentalRate)) return false;
        if (!Objects.equals(replacementCosts, film.replacementCosts))
            return false;
        return Objects.equals(lastUpdate, film.lastUpdate);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + releaseYear;
        result = 31 * result + rentalDuration;
        result = 31 * result + (rentalRate != null ? rentalRate.hashCode() : 0);
        result = 31 * result + (replacementCosts != null ? replacementCosts.hashCode() : 0);
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