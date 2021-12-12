package com.example.demo.repositories;

import com.example.demo.contracts.FilmProjection;
import com.example.demo.model.Film;
import com.example.demo.model.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface FilmsRepository extends JpaRepository<Film, Integer> {
    @Query(value = "select f from Film f where " +
            "(:id is null or f.id = :id) and " +
            "(:year is null or f.releaseYear = :year) and " +
            "(:title is null or f.title like %:title%) and " +
            "(:rental_duration is null or f.rentalDuration = :rental_duration) and " +
            "(:rental_rate is null or f.rentalRate = :rental_rate) and " +
            "(:replacement is null or f.replacementCosts = :replacement) and " +
            "(:language is null or f.language = :language)"
    )
    Page<FilmProjection> findAllFilms(
            Pageable pageable,
            @Param("id") Integer id,
            @Param("year") Integer year,
            @Param("title") String title,
            @Param("rental_duration") Integer rental_duration,
            @Param("rental_rate") BigDecimal rental_rate,
            @Param("replacement") BigDecimal replacement,
            @Param("language") Language language
    );

    Film findById(int id);
}
