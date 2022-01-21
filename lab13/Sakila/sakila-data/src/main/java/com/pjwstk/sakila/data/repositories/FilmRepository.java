package com.pjwstk.sakila.data.repositories;

import com.pjwstk.sakila.data.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
}
