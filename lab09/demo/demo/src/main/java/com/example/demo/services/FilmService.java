package com.example.demo.services;

import com.example.demo.contracts.FilmProjection;
import com.example.demo.model.Film;
import com.example.demo.model.Language;
import com.example.demo.repositories.FilmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FilmService {
    private FilmsRepository repo;

    @Autowired
    public FilmService(FilmsRepository repo) {
        this.repo = repo;
    }

    public List<FilmProjection> getAllFilms(int page, Language language, Integer release_year, Integer id, String title, Integer rental_duration, BigDecimal rental_rate, BigDecimal replacement_costs) {
        Pageable pageable = PageRequest.of(page, 15);

        return repo.findAllFilms(pageable, id, release_year, title, rental_duration, rental_rate, replacement_costs, language).getContent();
    }

    public int putFilm(int id, Film film) {
        Film repoFilm = repo.findById(id);

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
