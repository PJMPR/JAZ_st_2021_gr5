package com.example.demo.controllers;

import com.example.demo.contracts.LanguageProjection;
import com.example.demo.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/languages")
public class LanguageController {
    private final LanguageService service;

    @Autowired
    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LanguageProjection>> getLanguages() {
        List<LanguageProjection> languages = service.getLanguages();
        return ResponseEntity.ok(languages);
    }

}
