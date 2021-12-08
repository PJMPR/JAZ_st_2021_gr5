package com.example.demo.repositories;

import com.example.demo.model.FilmCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmCategoryRepo extends JpaRepository<FilmCategory, Integer> {

}
