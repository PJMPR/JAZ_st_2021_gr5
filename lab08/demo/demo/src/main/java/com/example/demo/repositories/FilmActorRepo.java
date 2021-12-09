package com.example.demo.repositories;
import com.example.demo.model.FilmActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface FilmActorRepo extends JpaRepository<FilmActor, Integer> {
    @Query("SELECT '*' FROM FilmActor fa WHERE fa.film.title = :title")
    Collection<FilmActor> getFilmActorsByFilmTitle(@Param("title") String title);
}
