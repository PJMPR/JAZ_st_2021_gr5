package com.example.demo.controllers;

import com.example.demo.contracts.FilmProjection;
import com.example.demo.model.Film;
import com.example.demo.model.Language;
import com.example.demo.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("api/films")
public class FilmsController {
    private final FilmService service;

    @Autowired
    public FilmsController(FilmService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FilmProjection>> getFilms(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) Language language,
            @RequestParam(required = false) Integer release_year,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer rental_duration,
            @RequestParam(required = false) BigDecimal rental_rate,
            @RequestParam(required = false) BigDecimal replacement_costs
    ) {
        List<FilmProjection> films = service.getAllFilms(page - 1, language, release_year, id, title,
                rental_duration, rental_rate, replacement_costs);

        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createFilm(@RequestBody Film film) {
        long createdId = service.addFilm(film);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateFilm(@PathVariable int id, @RequestBody Film film) {
        int updatedId = service.putFilm(id, film);
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteFilm(@PathVariable int id) {
        int deletedId = service.deleteFilm(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }
}
