package com.pjwstk.sakila.data.repositories;

import com.pjwstk.sakila.data.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    Language findFirstByName(String name);
}
