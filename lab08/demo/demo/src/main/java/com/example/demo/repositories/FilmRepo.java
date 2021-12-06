package com.example.demo.repositories;

import com.example.demo.model.Film;
import com.example.demo.repositories.projections.IFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepo extends JpaRepository<Film, Integer> {
    @Query(value = "select f from Film f")
    List<IFilm> getAllFilms();
}
