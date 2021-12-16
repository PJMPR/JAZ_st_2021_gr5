package com.example.demo.repositories;

import com.example.demo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LangRepository extends JpaRepository<Language, Integer> {
    @Query(value = "select l from Language l order by l.name")
    List<LanguageProjection> findAllLanguages();
}
