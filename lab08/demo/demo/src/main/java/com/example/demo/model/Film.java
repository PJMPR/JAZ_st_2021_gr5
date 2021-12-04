package com.example.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Film {
    private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private int length;
    private String rating;
    private Timestamp lastUpdate;
    private Language language;
    private Collection<FilmActor> filmActors;
    private Collection<FilmCategory> filmCategories;

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
    @Column(name = "length")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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

        if (filmId != film.filmId) return false;
        if (releaseYear != film.releaseYear) return false;
        if (length != film.length) return false;
        if (title != null ? !title.equals(film.title) : film.title != null) return false;
        if (description != null ? !description.equals(film.description) : film.description != null) return false;
        if (rating != null ? !rating.equals(film.rating) : film.rating != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(film.lastUpdate) : film.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = filmId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + releaseYear;
        result = 31 * result + length;
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

    @OneToMany(mappedBy = "film")
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
}
