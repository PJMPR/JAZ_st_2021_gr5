package com.example.demo.services;

import com.example.demo.contracts.FilmProjection;
import com.example.demo.model.Film;
import com.example.demo.repositories.FilmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private FilmsRepository repo;

    @Autowired
    public FilmService(FilmsRepository repo) {
        this.repo = repo;
    }

    public List<FilmProjection> getAllFilms(int page) {
        if (page > 49) page = 49;

        Pageable pageable = PageRequest.of(page, 20);
        return repo.findAllFilms(pageable);
    }

    public int putFilm(int id, Film film) {
        Film repoFilm = repo.findById(id);

        repoFilm.setTitle(film.getTitle());
        repoFilm.setTitle(film.getTitle());
        repoFilm.setLanguage(film.getLanguage());
        repoFilm.setReleaseYear(film.getReleaseYear());
        repoFilm.setRentalDuration(film.getRentalDuration());
        repoFilm.setRentalRate(film.getRentalRate());
        repoFilm.setReplacementCosts(film.getReplacementCosts());

        return repo.save(repoFilm).getId();
    }

    public int deleteFilm(int id) {
        repo.deleteById(id);
        return id;
    }

    public long addFilm(Film film) {
        return repo.save(film).getId();
    }
}
