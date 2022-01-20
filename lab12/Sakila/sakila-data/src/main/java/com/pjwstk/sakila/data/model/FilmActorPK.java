package com.pjwstk.sakila.data.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class FilmActorPK implements Serializable {
    private Long actorId;
    private Long filmId;

    @Column(name = "actor_id")
    @Id
    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    @Column(name = "film_id")
    @Id
    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmActorPK that = (FilmActorPK) o;

        if (actorId != null ? !actorId.equals(that.actorId) : that.actorId != null) return false;
        if (filmId != null ? !filmId.equals(that.filmId) : that.filmId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actorId != null ? actorId.hashCode() : 0;
        result = 31 * result + (filmId != null ? filmId.hashCode() : 0);
        return result;
    }
}
