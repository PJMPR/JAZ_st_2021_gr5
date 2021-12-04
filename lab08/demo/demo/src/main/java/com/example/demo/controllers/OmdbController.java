package com.example.demo.controllers;

import com.example.demo.contract.MovieDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

public class OmdbController {

    RestTemplate rest;

    public OmdbController(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity getData(@PathVariable("id") int id){
        var movie = rest.getForEntity("http://www.omdbapi.com/?apikey=" +
                System.getenv("OmdbApiKey") + "&", MovieDto.class).getBody();
        return ResponseEntity.ok(movie);
    }
}
