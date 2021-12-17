package com.example.demo.controllers;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.repositories.FilmsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("api/films")
@RequiredArgsConstructor
public class FilmsController {

    private final FilmsRepository filmsRepository;


    @GetMapping
    public ResponseEntity<List<FilmDto>> getFilms(@RequestParam(defaultValue = "1")  Integer page,
                                                  @RequestParam(required = false)  Integer language,
                                                  @RequestParam(required = false) Integer id,
                                                  @RequestParam(required = false) String title,
                                                  @RequestParam(required = false) Integer release_year,
                                                  @RequestParam(required = false) BigDecimal rental_duration,
                                                  @RequestParam(required = false) BigDecimal rental_rate,
                                                  @RequestParam(required = false) BigDecimal replacement_costs) {
        FilmDto film = FilmDto.builder()
                .id(id)
                .title(title)
                .releaseYear(release_year)
                .rentalDuration(rental_duration)
                .rentalRate(rental_rate)
                .replacementCosts(replacement_costs)
                .language(LanguageDto.builder().id(language).name("").build()).build();


        return ResponseEntity.ok(filmsRepository.getFilmsByPage(page, 10,film));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createFilm(@RequestBody FilmDto newFilm) {
        if (newFilm.getLanguage().getId() == 7) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(filmsRepository.createFilm(newFilm)).build();

    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteFilm(@PathVariable int id) {
        return ResponseEntity.status(filmsRepository.deleteFilmById(id)).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateFilm(@PathVariable int id, @RequestBody FilmDto film) {
        return ResponseEntity.status(filmsRepository.updateFilm(film)).build();
    }
}
