package com.example.demo.repositories;

import com.example.demo.contracts.LanguageDto;
import com.example.demo.model.Language;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class LanguageRepository {
    private final EntityManager entityManager;

    public List<LanguageDto> getLanguages(){
        return entityManager.createQuery(
                "SELECT language from Language language", Language.class
        ).getResultList()
                .stream()
                .map(language -> new LanguageDto(language.getLanguageId(), language.getName()))
                .collect(Collectors.toList());
    }
}
