package com.example.demo.controllers;

import com.example.demo.service.TheMovieDbServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
@RequestMapping("moviesclient")
@RequiredArgsConstructor
public class TheMovieDbController {

    private final TheMovieDbServiceClient client;

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity getData(@PathVariable("id") int id){
        var movie = client.getMovie(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/updater/status")
    public ResponseEntity getSystemStatus(){
        return ResponseEntity.ok(client.getSystemStatusInfo());
    }


    @GetMapping
    @RequestMapping("/updater/reload")
    public ResponseEntity reloadData() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return ResponseEntity.ok(client.reloadData());
    }

    @GetMapping
    @RequestMapping("/updater/reload/{year}")
    public ResponseEntity reloadDataByYear(@PathVariable int year) {
        return ResponseEntity.ok(client.reloadDataByYear(year));
    }
}
