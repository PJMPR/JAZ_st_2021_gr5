package com.pjwstk.sakila.filmsupdater;

import info.movito.themoviedbapi.TmdbApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TheMovieDbConfiguration {

    @Bean
    public TmdbApi tmdbApi(@Value("${tmdb.api.key}") String apiKey){
        return new TmdbApi(apiKey);
    }
}
