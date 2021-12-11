package com.example.demo.repositories;

import com.example.demo.contracts.FilmProjection;
import com.example.demo.model.Film;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmsRepository extends PagingAndSortingRepository<Film, Integer> {
    @Query(value = "select f from Film f")
    List<FilmProjection> findAllFilms(Pageable pageable);
}
