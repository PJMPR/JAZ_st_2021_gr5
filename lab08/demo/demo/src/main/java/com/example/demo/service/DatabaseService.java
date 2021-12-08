package com.example.demo.service;

import com.example.demo.Frame.MovieDPage;
import com.example.demo.contract.MovieDto;
import com.example.demo.info.TimerInfo;
import com.example.demo.job.DatabaseChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
public class DatabaseService {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DatabaseService.class));
    FileHandler fh;

    RestTemplate rest;
    private final SchedulerService scheduler;

    @Autowired
    public DatabaseService(RestTemplate rest, final SchedulerService scheduler) {
        try {
            fh = new FileHandler("demo/src/main/java/com/example/demo/Log.txt", true);
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.info("Logger started");
        }catch (IOException e) {
            e.printStackTrace();
        }

        this.rest = rest;
        this.scheduler = scheduler;
        runDatabaseChecker();
    }

    public void runDatabaseChecker(){
        final TimerInfo info = new TimerInfo();
        info.setRunForever(false);
        info.setRepeatIntervalHs(24);
        info.setStartHour(3);

        scheduler.schedule(DatabaseChecker.class, info);
    }

    public MovieDto getData(int id) {
        LOGGER.info("getData(" + id + ") request received");
        return rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id +
                "?api_key=" + System.getenv("TheMovieDbApiKey"), MovieDto.class).getBody();
    }

    public List<MovieDto> getMovies(int yearFrom, int yearTo){
        ArrayList<MovieDto> list = new ArrayList<>();
        for(int i = yearFrom; i <= yearTo; i++){
            MovieDPage page = rest.getForEntity("https://api.themoviedb.org/3/discover/movie?api_key=" + System.getenv("TheMovieDbApiKey") +
                    "&primary_release_year=" + i, MovieDPage.class).getBody();
            for (int j = 1; j < page.getTotal_pages(); j++) {
                page = rest.getForEntity("https://api.themoviedb.org/3/discover/movie?api_key=" + System.getenv("TheMovieDbApiKey") +
                        "&primary_release_year=" + i + "&page=" + j, MovieDPage.class).getBody();
                list.addAll(page.getResults());
            }
        }
        return list;
    }

    public String reloadData() {
        return "ok";
    }

    public String reloadDataByYear(int year) {
        return "ok";
    }
}