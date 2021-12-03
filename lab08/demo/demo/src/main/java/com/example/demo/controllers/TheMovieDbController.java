package com.example.demo.controllers;

import com.example.demo.contract.MovieDto;
import com.example.demo.contract.OMBDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("moviesclient")
public class TheMovieDbController {

    RestTemplate rest;

    public TheMovieDbController(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping
    @RequestMapping("/moviedb/{id}")
    public ResponseEntity getDataFromMovieDB(@PathVariable("id") int id){
        var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id +
                "?api_key=" + System.getenv("TheMovieDbApiKey"), MovieDto.class).getBody();
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    @RequestMapping("/omb/{id}")
    public ResponseEntity getDataFromOMDb(@PathVariable("id") String id){
        var movie = rest.getForEntity("http://www.omdbapi.com/?apikey=" +
                System.getenv("OMDbAPIKey") +
                "&i=" + id, OMBDto.class).getBody();
        return ResponseEntity.ok(movie);
    }

}
