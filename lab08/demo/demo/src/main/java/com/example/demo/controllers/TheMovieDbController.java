package com.example.demo.controllers;

import com.example.demo.FilmService.FilmService;
import com.example.demo.contract.IMDBMovieDto;
import com.example.demo.contract.MovieDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("moviesclient")
public class TheMovieDbController {

//    RestTemplate rest;

//    public TheMovieDbController(RestTemplate rest) {
//        this.rest = rest;
//    }

    FilmService filmService;

    public TheMovieDbController(FilmService filmService){
        this.filmService = filmService;
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<MovieDto> getData(@PathVariable("id") int id){
        return ResponseEntity.ok(filmService.getMoveInfo(id));
    }

    @GetMapping
    @RequestMapping("/imdb/{id}")
    public ResponseEntity<IMDBMovieDto> getDataIMDB(@PathVariable("id") String id){
        return ResponseEntity.ok(filmService.getMovieInfoFromIMDB(id));
    }


//    @GetMapping
//    @RequestMapping("/imdb/{id}")
//    public ResponseEntity<IMDBMovieDto> getActorsIMDB(@PathVariable("id") String id){
//        return ResponseEntity.ok(filmService.getMovieInfoFromIMDB(id));
//    }
}
