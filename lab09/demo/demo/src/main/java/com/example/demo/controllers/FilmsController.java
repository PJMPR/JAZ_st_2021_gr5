package com.example.demo.controllers;

import com.example.demo.contracts.FilmProjection;
import com.example.demo.model.Film;
import com.example.demo.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<FilmProjection>> getFilms(@RequestParam(defaultValue = "0", required = false) int page) {
        List<FilmProjection> films = service.getAllFilms(page);
        return new ResponseEntity<>(films, HttpStatus.OK);
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
