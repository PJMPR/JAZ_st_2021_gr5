package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class FilmCategoryPK implements Serializable {
    private int filmId;
    private int categoryId;

    @Column(name = "film_id")
    @Id
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Column(name = "category_id")
    @Id
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmCategoryPK that = (FilmCategoryPK) o;

        if (filmId != that.filmId) return false;
        if (categoryId != that.categoryId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = filmId;
        result = 31 * result + categoryId;
        return result;
    }
}
