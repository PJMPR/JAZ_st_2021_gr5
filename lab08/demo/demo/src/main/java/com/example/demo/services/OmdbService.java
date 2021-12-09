package com.example.demo.services;

import com.example.demo.contract.MovieFromOmdb;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmdbService {
    private RestTemplate restTemplate = new RestTemplate();
    private String API_URL = "http://www.omdbapi.com/";
    private String apiKey = System.getenv("omdbApiKey");

    public MovieFromOmdb getMovie(String title) {
        var movie = restTemplate.getForObject(API_URL + "?t=" + title + "&apiKey=" + apiKey, MovieFromOmdb.class);
        return movie;
    }
}
