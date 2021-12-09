package com.example.demo.controllers;

import com.example.demo.repositories.*;
import com.example.demo.updater.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public void reloadSakila(@RequestParam(required = false, defaultValue = "1980") int year) {
        new Updater().update(rest, categoryRepo, filmCatRepo, languageRepo,
                        actorsRepo, filmActorsRepo, filmRepo, year);
    }
}
