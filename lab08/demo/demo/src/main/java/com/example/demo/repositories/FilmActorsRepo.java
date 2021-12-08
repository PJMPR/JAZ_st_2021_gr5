package com.example.demo.repositories;

import com.example.demo.model.FilmActor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmActorsRepo extends JpaRepository<FilmActor, Integer> {
}
