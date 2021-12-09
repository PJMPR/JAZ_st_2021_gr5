package com.example.demo.controllers;

import com.example.demo.contract.MovieFromMovieDb;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("moviesClient")
public class TheMovieDbController {

    RestTemplate rest;
    String title;

    public TheMovieDbController(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity getDataByMovieId(@PathVariable("id") int id){
        var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id +
                "?api_key=" + System.getenv("apiKey"), MovieFromMovieDb.class).getBody().getTitle();
        title = movie;
        return ResponseEntity.ok(title);
    }
}
