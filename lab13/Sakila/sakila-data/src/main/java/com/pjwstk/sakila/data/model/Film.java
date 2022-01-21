package com.pjwstk.sakila.data.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Film {
    private Long id;
    private String title;
    private String description;
    private int releaseYear;
    private int rentalDuration;
    private BigDecimal rentalRate;
    private int length;
    private BigDecimal replacementCost;
    private String rating;
    private Timestamp lastUpdate;
    private Language language;
    private Collection<FilmActor> filmActors;
    private Collection<FilmCategory> filmCategories;
    private Collection<Inventory> inventories;

    @Id
    @Column(name = "film_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    @Column(name = "length")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
    @Column(name = "rating")
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

        if (releaseYear != film.releaseYear) return false;
        if (rentalDuration != film.rentalDuration) return false;
        if (length != film.length) return false;
        if (id != null ? !id.equals(film.id) : film.id != null) return false;
        if (title != null ? !title.equals(film.title) : film.title != null) return false;
        if (description != null ? !description.equals(film.description) : film.description != null) return false;
        if (rentalRate != null ? !rentalRate.equals(film.rentalRate) : film.rentalRate != null) return false;
        if (replacementCost != null ? !replacementCost.equals(film.replacementCost) : film.replacementCost != null)
            return false;
        if (rating != null ? !rating.equals(film.rating) : film.rating != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(film.lastUpdate) : film.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + releaseYear;
        result = 31 * result + rentalDuration;
        result = 31 * result + (rentalRate != null ? rentalRate.hashCode() : 0);
        result = 31 * result + length;
        result = 31 * result + (replacementCost != null ? replacementCost.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
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

    @OneToMany(mappedBy = "filmByFilmId")
    public Collection<FilmActor> getFilmActors() {
        return filmActors;
    }

    public void setFilmActors(Collection<FilmActor> filmActors) {
        this.filmActors = filmActors;
    }

    @OneToMany(mappedBy = "film")
    public Collection<FilmCategory> getFilmCategories() {
        return filmCategories;
    }

    public void setFilmCategories(Collection<FilmCategory> filmCategories) {
        this.filmCategories = filmCategories;
    }

    @OneToMany(mappedBy = "film")
    public Collection<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Collection<Inventory> inventories) {
        this.inventories = inventories;
    }
}
