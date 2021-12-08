package com.example.demo.controllers;

import com.example.demo.contract.MovieDto;
import com.example.demo.contract.OMDbDto;
import com.example.demo.repositories.*;
import com.example.demo.updater.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("updater")
public class UpdaterController {

    private final RestTemplate rest;
    private final CategoryRepo categoryRepo;
    private final FilmCategoryRepo filmCatRepo;
    private final LanguageRepo languageRepo;
    private final ActorsRepo actorsRepo;
    private final FilmActorsRepo filmActorsRepo;
    private final FilmRepo filmRepo;

    @Autowired
    public UpdaterController(RestTemplate rest, CategoryRepo categoryRepo, FilmCategoryRepo filmCatRepo, LanguageRepo languageRepo, ActorsRepo actorsRepo, FilmActorsRepo filmActorsRepo, FilmRepo filmRepo) {
        this.rest = rest;
        this.categoryRepo = categoryRepo;
        this.filmCatRepo = filmCatRepo;
        this.languageRepo = languageRepo;
        this.actorsRepo = actorsRepo;
        this.filmRepo = filmRepo;
        this.filmActorsRepo = filmActorsRepo;
    }

    @GetMapping
    @RequestMapping("/reload")
    public void reloadSakila() {
        Chain link1 = new LanguageUpdate(languageRepo);
        Chain link2 = new FilmUpdate(filmRepo, languageRepo);
        Chain link3 = new CategoryUpdate(categoryRepo, filmCatRepo, filmRepo);
        Chain link4 = new ActorsUpdate(actorsRepo, filmActorsRepo, filmRepo);


        link1.setNextChain(link2);
        link2.setNextChain(link3);
        link3.setNextChain(link4);

        int id = 2;
        while (true) {
            try {
                var dataFromMovieDB = rest.getForEntity("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + System.getenv("TheMovieDbApiKey"), MovieDto.class).getBody();
                var dataFromOMDb = rest.getForEntity("http://www.omdbapi.com/?apikey=" + System.getenv("OMDbAPIKey") + "&i=" + dataFromMovieDB.getImdb_id(), OMDbDto.class).getBody();

                if (Integer.parseInt((dataFromMovieDB.getRelease_date().split("-"))[0]) >= 1980 && !dataFromMovieDB.getImdb_id().equals("")) {
                    link1.query(dataFromMovieDB, dataFromOMDb);
                }
                id++;
            } catch (HttpClientErrorException e) {
                id++;
            }
        }
    }
}
