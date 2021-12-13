package com.example.demo.controllers;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.model.Film;
import com.example.demo.repositories.FilmsRepository;
import com.example.demo.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/films")
@RequiredArgsConstructor
public class FilmsController {

    private final FilmsRepository filmsRepository;


    @GetMapping
    public ResponseEntity<List<Film>> getFilms(@RequestParam int page){
        List<Film> films = filmsRepository.getFilmsByPage(page,10);
        return ResponseEntity.ok(films);
    }

    @DeleteMapping()
    public ResponseEntity deleteFilm(@RequestParam int id){
        filmsRepository.deleteFilmById(id);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("{id}")
//    public ResponseEntity updateFilm(@PathVariable int id,@RequestBody FilmDto film){
//        var filmToUpdate = films.stream().filter(x->x.getId()==film.getId()).findFirst().get();
//        films.remove(filmToUpdate);
//        films.add(film);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("db")
    public ResponseEntity getFilmsFromDb(@RequestParam int page, @RequestParam int size){
        var films = filmsRepository.getFilmsByPage(page,size);
        return ResponseEntity.ok(films);
    }

}
