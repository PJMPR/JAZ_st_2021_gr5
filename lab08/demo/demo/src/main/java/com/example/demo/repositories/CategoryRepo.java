package com.example.demo.repositories;

import com.example.demo.model.Category;
import com.example.demo.repositories.projections.ICategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    @Query(value = "select c from Category c")
    List<ICategoryName> getAllCategories();
}
