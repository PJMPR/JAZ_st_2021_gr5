package com.example.demo.services;

import com.example.demo.contracts.LanguageProjection;
import com.example.demo.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository repo;

    @Autowired
    public LanguageService(LanguageRepository repo) {
        this.repo = repo;
    }

    public List<LanguageProjection> getLanguages(){
        return repo.findAllLanguages();
    }
}
