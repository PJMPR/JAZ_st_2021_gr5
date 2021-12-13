package com.example.demo.controllers;


import com.example.demo.model.Language;
import com.example.demo.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/languages")
public class LanguageController {

    LanguageRepository languageRepository;

    @Autowired
    public LanguageController(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @GetMapping
    public ResponseEntity<List<Language>> getLanguages() {
        return ResponseEntity.ok(languageRepository.getLanguages());
    }
}
