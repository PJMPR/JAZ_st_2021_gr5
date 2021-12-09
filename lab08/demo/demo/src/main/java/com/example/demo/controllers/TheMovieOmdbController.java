package com.example.demo.controllers;

import com.example.demo.contract.MovieFromOmdb;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("getMovie")
public class TheMovieOmdbController {

    RestTemplate rest;

    @GetMapping
    @RequestMapping("{title}")
    public ResponseEntity getDataByMovieTitle(@PathVariable("title") String title) {
        var movie = rest.getForEntity("https://www.omdbapi.com/?t='" + title + "'&apikey=" + System.getenv("omdbApiKey"), MovieFromOmdb.class).getBody();
        return ResponseEntity.ok(movie);
    }
}
