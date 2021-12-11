package com.example.demo.services;

import com.example.demo.contracts.FilmProjection;
import com.example.demo.repositories.FilmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmService {
    private FilmsRepository repo;

    @Autowired
    public FilmService(FilmsRepository repo) {
        this.repo = repo;
    }

    public List<FilmProjection> getAllFilms(int page){
        Pageable pageable = PageRequest.of(page, 20);
        return repo.findAllFilms(pageable);
    }
}
