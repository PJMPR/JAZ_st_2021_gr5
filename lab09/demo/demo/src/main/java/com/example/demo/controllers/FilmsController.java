package com.example.demo.controllers;

import com.example.demo.contracts.FilmProjection;
import com.example.demo.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<List<FilmProjection>> getFilms(@RequestParam(defaultValue = "0", required = false) int page){
        List<FilmProjection> films = service.getAllFilms(page);
        return ResponseEntity.ok(films);
    }
}
