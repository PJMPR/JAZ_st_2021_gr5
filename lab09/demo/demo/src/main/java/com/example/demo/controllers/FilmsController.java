package com.example.demo.controllers;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.repositories.FilmsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/films")
@RequiredArgsConstructor
public class FilmsController {

    private final FilmsRepository filmsRepository;

    private static List<FilmDto> films = List.of(
            new FilmDto(1, "tytul", 2021, new LanguageDto(1,"polish"), new BigDecimal(2), new BigDecimal(2.99),new BigDecimal(20.99)),
            new FilmDto(2, "tytul", 2021, new LanguageDto(1,"polish"), new BigDecimal(2), new BigDecimal(2.99),new BigDecimal(20.99)),
            new FilmDto(3, "tytul", 2021, new LanguageDto(1,"polish"), new BigDecimal(2), new BigDecimal(2.99),new BigDecimal(20.99)),
            new FilmDto(4, "tytul", 2021, new LanguageDto(1,"polish"), new BigDecimal(2), new BigDecimal(2.99),new BigDecimal(20.99)),
            new FilmDto(5, "tytul", 2021, new LanguageDto(1,"polish"), new BigDecimal(2), new BigDecimal(2.99),new BigDecimal(20.99)),
            new FilmDto(6, "tytul", 2021, new LanguageDto(1,"polish"), new BigDecimal(2), new BigDecimal(2.99),new BigDecimal(20.99)),
            new FilmDto(7, "tytul", 2021, new LanguageDto(1,"polish"), new BigDecimal(2), new BigDecimal(2.99),new BigDecimal(20.99)),
            new FilmDto(8, "tytul", 2021, new LanguageDto(1,"polish"), new BigDecimal(2), new BigDecimal(2.99),new BigDecimal(20.99))

    ).stream().collect(Collectors.toList());

    @GetMapping
    public ResponseEntity<List<FilmDto>> getFilms(){
        return ResponseEntity.ok(films);
    }

    @PutMapping("{id}")
    public ResponseEntity updateFilm(@PathVariable int id,@RequestBody FilmDto film){
        var filmToUpdate = films.stream().filter(x->x.getId()==film.getId()).findFirst().get();
        films.remove(filmToUpdate);
        films.add(film);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("db")
    public ResponseEntity getFilmsFromDb(@RequestParam int page, @RequestParam int size){
        var films = filmsRepository.getFilmsByPage(page,size);
        return ResponseEntity.ok(films);
    }

}
