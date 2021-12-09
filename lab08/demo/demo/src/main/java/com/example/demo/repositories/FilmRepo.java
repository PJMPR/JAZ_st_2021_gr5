package com.example.demo.repositories;
import com.example.demo.model.Film;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;

@Repository
public interface FilmRepo extends JpaRepository<Film, Integer> {
    @Query("SELECT f FROM Film f")
    Collection<Film> getAllMovies();

    @Modifying
    @Query("UPDATE Film f SET f.description = :description WHERE f.title = :title")
    void updateFilmDescription(@Param("description") String description, @Param("title") String title);

    @Modifying
    @Query("UPDATE Film f SET f.language.languageId = :languageId WHERE f.title = :title")
    void updateFilmLanguage(@Param("languageId") Integer languageId, @Param("title") String title);

    @Modifying
    @Query("UPDATE Film f SET f.length = :length WHERE f.title = :title")
    void updateFilmRuntime(@Param("length") Integer length, @Param("title") String title);
}
