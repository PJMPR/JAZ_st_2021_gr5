package com.example.demo.services;
import com.example.demo.contract.MovieFromMovieDb;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TheMovieDbService {
    private RestTemplate restTemplate = new RestTemplate();
    private String API_URL = "https://api.themoviedb.org/3/find/";
    private String apiKey = System.getenv("apiKey");

    public MovieFromMovieDb getMovieFromMovieDb(String imdbId) {
        var movie = restTemplate.getForObject(API_URL + imdbId + "?api_key=" + apiKey, MovieFromMovieDb.class);
        return movie;
    }
}
