package com.example.demo.controllers;

import com.example.demo.contract.GenreDto;
import com.example.demo.contract.MovieDto;
import com.example.demo.contract.OMDbDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("moviesclient")
public class TheMovieDbController {

    RestTemplate rest;

    @Autowired
    public TheMovieDbController(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping
    @RequestMapping("/moviedb/{id}")
    public ResponseEntity getDataFromMovieDB(@PathVariable("id") int id) {
        try {
            var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + System.getenv("TheMovieDbApiKey"), MovieDto.class).getBody();
            return ResponseEntity.status(HttpStatus.OK).body(movie);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie with that id doesn't exist");
        }
    }

    @GetMapping
    @RequestMapping("/moviedb/genre")
    public ResponseEntity getGenreFromMovieDB() {
        var genres = rest.getForEntity("https://api.themoviedb.org/3/genre/movie/list" + "?api_key=" + System.getenv("TheMovieDbApiKey"), GenreDto.class).getBody();
        return ResponseEntity.status(HttpStatus.OK).body(genres);
    }

    @GetMapping
    @RequestMapping("/omdb/{id}")
    public ResponseEntity getDataFromOMDb(@PathVariable("id") String id) {
        var movie = rest.getForEntity("http://www.omdbapi.com/?apikey=" + System.getenv("OMDbAPIKey") + "&i=" + id, OMDbDto.class).getBody();
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }
}
