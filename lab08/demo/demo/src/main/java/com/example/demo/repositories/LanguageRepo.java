package com.example.demo.repositories;
import com.example.demo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LanguageRepo extends JpaRepository<Language, Integer> {
    @Query("SELECT l.languageId FROM Language l WHERE l.name = :language")
    Collection<Language> getLanguageByLanguageName(@Param("language") String language);
}
