package com.example.demo.data;

import javax.persistence.*;

@Entity
@Table(name = "film_text", schema = "sakila", catalog = "")
public class FilmText {
    private short filmId;
    private String title;
    private String description;

    @Id
    @Column(name = "film_id")
    public short getFilmId() {
        return filmId;
    }

    public void setFilmId(short filmId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmText filmText = (FilmText) o;

        if (filmId != filmText.filmId) return false;
        if (title != null ? !title.equals(filmText.title) : filmText.title != null) return false;
        if (description != null ? !description.equals(filmText.description) : filmText.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) filmId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
