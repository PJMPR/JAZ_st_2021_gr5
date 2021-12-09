package com.example.demo.controller;

import com.example.demo.contract.IMDBMovieDto;
import com.example.demo.service.DatabaseService;
import com.example.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping("moviesclient")
public class TheMovieDbController{
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TheMovieDbController.class));
    FileHandler fh;

    final DatabaseService databaseService;
    final FilmService filmService;

    @Autowired
    public TheMovieDbController(DatabaseService databaseService, FilmService filmService) {
        this.databaseService = databaseService;
        this.filmService = filmService;
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

    @PostMapping("/updater/status")
    public ResponseEntity getSystemStatus(){
        return ResponseEntity.ok(databaseService.getSystemStatusInfo());
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity getData(@PathVariable("id") int id){
        return ResponseEntity.ok(databaseService.getData(id));
    }

    @GetMapping
    @RequestMapping("/updater/reload")
    public ResponseEntity reloadData() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return ResponseEntity.ok(databaseService.reloadData());
    }

    @GetMapping
    @RequestMapping("/updater/reload/{year}")
    public ResponseEntity reloadDataByYear(@PathVariable int year) {
        return ResponseEntity.ok(databaseService.reloadDataByYear(year));
    }

    @GetMapping
    @RequestMapping("/imdb/{id}")
    public ResponseEntity<IMDBMovieDto> getDataIMDB(@PathVariable("id") String id){
        return ResponseEntity.ok(filmService.getMovieInfoFromIMDB(id));
    }
}