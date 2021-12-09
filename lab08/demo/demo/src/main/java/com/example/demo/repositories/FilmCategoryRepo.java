package com.example.demo.repositories;
import com.example.demo.model.FilmCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface FilmCategoryRepo extends JpaRepository<FilmCategory, Integer> {
    @Query("SELECT '*' FROM FilmCategory fc WHERE fc.film.title = :title")
    Collection<FilmCategory> getFilmCategoriesByFilmTitle(@Param("title") String title);
}
