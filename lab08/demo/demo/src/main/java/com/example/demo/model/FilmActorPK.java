package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class FilmActorPK implements Serializable {
    private int actorId;
    private int filmId;

    @Column(name = "actor_id")
    @Id
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Column(name = "film_id")
    @Id
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmActorPK that = (FilmActorPK) o;

        if (actorId != that.actorId) return false;
        if (filmId != that.filmId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actorId;
        result = 31 * result + filmId;
        return result;
    }
}
