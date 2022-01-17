package com.pjwstk.sakila.filmsupdater.selftest;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.pjwstk.sakila.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@NoArgsConstructor
public class TMDBConnectionSelftest implements Selftest {

    RestTemplate rest;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Override
    public SelfTestResult run() {
        SelfTestResult result;
        try {
            var response = rest.getForEntity("https://api.themoviedb.org/3/movie/1"+
                    "?api_key=" + System.getenv("TheMovieDbApiKey"),String.class).getBody();
            if(!response.isBlank() && !response.isEmpty() && response != null){
                result = SelfTestResult.builder()
                        .name("TheMovieDB api connection test")
                        .description("Checks if there is a connection with api.themoviedb.org")
                        .passed(true)
                        .errors(null)
                        .build();
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            result = SelfTestResult.builder()
                    .name("TheMovieDB api connection test")
                    .description("Checks if there is a connection with api.themoviedb.org")
                    .passed(false)
                    .errors(List.of("No connection with TheMovieDB API. Check your internet connection",e.getMessage()))
                    .build();
        }
        return result;
    }
}
