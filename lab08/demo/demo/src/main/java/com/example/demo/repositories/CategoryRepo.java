package com.example.demo.repositories;
import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    @Query("SELECT c.name FROM Category c")
    Collection<Category> getCategories();
}
