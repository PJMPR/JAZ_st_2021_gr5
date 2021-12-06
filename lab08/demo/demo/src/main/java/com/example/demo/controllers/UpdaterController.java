package com.example.demo.controllers;

import com.example.demo.contract.MovieDto;
import com.example.demo.contract.OMDbDto;
import com.example.demo.repositories.ActorsRepo;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.repositories.LanguageRepo;
import com.example.demo.updater.ActorsUpdate;
import com.example.demo.updater.CategoryUpdate;
import com.example.demo.updater.Chain;
import com.example.demo.updater.LanguageUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("updater")
public class UpdaterController {

    RestTemplate rest;
    CategoryRepo categoryRepo;
    LanguageRepo languageRepo;
    ActorsRepo actorsRepo;

    @Autowired
    public UpdaterController(RestTemplate rest, CategoryRepo categoryRepo, LanguageRepo languageRepo, ActorsRepo actorsRepo) {
        this.rest = rest;
        this.categoryRepo = categoryRepo;
        this.languageRepo = languageRepo;
        this.actorsRepo = actorsRepo;
    }

    @GetMapping
    @RequestMapping("/reload")
    public void reloadSakila() {
        Chain link1 = new CategoryUpdate(categoryRepo);
        Chain link2 = new LanguageUpdate(languageRepo);
        Chain link3 = new ActorsUpdate(actorsRepo);

        link1.setNextChain(link2);
        link2.setNextChain(link3);

        int id = 2;
        while (true) {
            try {
                var dataFromMovieDB = rest.getForEntity("https://api.themoviedb.org/3/movie/" + id + "?api_key=" + System.getenv("TheMovieDbApiKey"), MovieDto.class).getBody();
                var dataFromOMDb = rest.getForEntity("http://www.omdbapi.com/?apikey=" + System.getenv("OMDbAPIKey") + "&i=" + dataFromMovieDB.getImdb_id(), OMDbDto.class).getBody();

                if (Integer.parseInt((dataFromMovieDB.getRelease_date().split("-"))[0]) >= 1980) {
                    link1.query(dataFromMovieDB, dataFromOMDb);
                }
                id++;
            } catch (HttpClientErrorException e) {
                id++;
            }
        }
    }
}
