package com.example.demo.controllers;

import com.example.demo.Frame.MovieDPage;
import com.example.demo.contract.MovieDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping("moviesclient")
public class TheMovieDbController {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TheMovieDbController.class));
    FileHandler fh;

    RestTemplate rest;

    public TheMovieDbController(RestTemplate rest) {
        this.rest = rest;
        try {
            fh = new FileHandler("demo/src/main/java/com/example/demo/Log.txt", true);
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.info("Logger started");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity getData(@PathVariable("id") int id){
        LOGGER.info("getData(" + id + ") request received");
        var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id +
                "?api_key=" + System.getenv("TheMovieDbApiKey"), MovieDto.class).getBody();
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    @RequestMapping("/updater/reload")
    public ResponseEntity reloadData() {
        LOGGER.info("reloadData() request received");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return ResponseEntity.ok(getMovies(1980, year));
    }

    @GetMapping
    @RequestMapping("/updater/reload/{year}")
    public ResponseEntity reloadDataByYear(@PathVariable int year) {
        LOGGER.warning("reloadDataByYear(" + year + ") request received");
        return ResponseEntity.ok(getMovies(year, year));
    }

    @GetMapping
    private List<MovieDto> getMovies(int yearFrom, int yearTo){
        ArrayList<MovieDto> list = new ArrayList<>();
        for(int i = yearFrom; i <= yearTo; i++){
            MovieDPage page = rest.getForEntity("https://api.themoviedb.org/3/discover/movie?api_key=" + System.getenv("TheMovieDbApiKey") +
                    "&language=en-US&year=" + i, MovieDPage.class).getBody();
            for (int j = 1; j < page.getTotal_pages(); j++) {
                page = rest.getForEntity("https://api.themoviedb.org/3/discover/movie?api_key=" + System.getenv("TheMovieDbApiKey") +
                        "&language=en-US&year=" + i + "&page=" + j, MovieDPage.class).getBody();
                for (MovieDto movie :
                        page.getResults()) {
                    list.add(movie);
                }
            }
        }
        return list;
    }
}