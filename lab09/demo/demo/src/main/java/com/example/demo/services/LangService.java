package com.example.demo.services;

import com.example.demo.repositories.LangRepository;
import com.example.demo.repositories.LanguageProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LangService {
    private final LangRepository repo;

    @Autowired
    public LangService(LangRepository repo) {
        this.repo = repo;
    }

    public List<LanguageProjection> getLanguages() {
        return repo.findAllLanguages();
    }
}
