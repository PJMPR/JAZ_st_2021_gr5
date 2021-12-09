package com.example.demo.service;

import com.example.demo.contract.MovieDto;
import com.example.demo.quartz.ScheduleInfo;
import com.example.demo.quartz.UpdateDB;
import com.example.demo.status.SystemStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class TheMovieDbServiceClient {
    private final RestTemplate rest;
    String apiKey;

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TheMovieDbServiceClient.class));
    FileHandler fileHandler;
    private final SystemStatus systemStatus;

    private final SchedulerService schedulerService;

    public MovieDto getMovie(int id) {
        var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id +
                "?api_key=" + apiKey, MovieDto.class).getBody();
        return movie;
    }

    public void runDatabaseChecker() {
        final ScheduleInfo info = new ScheduleInfo();
        info.setRunForever(false);
        info.setRepeatIntervalsHs(24);
        info.setStartHour(2);

        schedulerService.schedule(UpdateDB.class, info);
    }

    public SystemStatus getSystemStatusInfo() {
        return systemStatus;
    }

    public String reloadData() {
        return "reloading...";
    }

    public  String reloadDataByYear(int year) {
        return "reloading...";
    }
}
