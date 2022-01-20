package com.pjwstk.sakila.data.repositories;

import com.pjwstk.sakila.data.model.FilmCategory;
import com.pjwstk.sakila.data.model.FilmCategoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryPK> {
}
