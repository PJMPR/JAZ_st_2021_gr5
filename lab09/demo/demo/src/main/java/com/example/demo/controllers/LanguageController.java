package com.example.demo.controllers;


import com.example.demo.contracts.LanguageDto;
import com.example.demo.repositories.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageRepository languageRepository;


    @GetMapping
    public ResponseEntity<List<LanguageDto>> getLanguages() {
        List<LanguageDto> languages = languageRepository.getLanguages();
        languages.add(new LanguageDto(7,"All languages"));
        return ResponseEntity.ok(languages);
    }
}
