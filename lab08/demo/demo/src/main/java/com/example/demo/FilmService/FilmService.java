package com.example.demo.FilmService;

import com.example.demo.contract.IMDBMovieDto;
import com.example.demo.contract.MovieDto;
import com.example.demo.model.Actor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FilmService {
    RestTemplate rest;

    public FilmService(RestTemplate rest) {
        this.rest = rest;
    }

    public MovieDto getMoveInfo(int id) {
        var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id +
                "?api_key=" + System.getenv("TMDBapiKey"), MovieDto.class).getBody();

        if (movie != null) {
            var movieIMDB = getMovieInfoFromIMDB(movie.getImdb_id());
            BeanUtils.copyProperties(movieIMDB,movie);
            List<Actor> actors = new ArrayList<>();
            String[] names = movieIMDB.getActors().split(",");
            for (String s : names) {
                String[] parts = s.strip().split(" ");
                actors.add(new Actor(parts[0],parts[1]));
            }
            movie.setActors(actors);
        }

        return movie;
    }

    public IMDBMovieDto getMovieInfoFromIMDB(String id) {

        return rest.getForEntity("http://www.omdbapi.com/?apikey="
                + System.getenv("OMDBapiKey") +
                "&i=" + id, IMDBMovieDto.class).getBody();
    }

//    public ActorsDto getActorsInfoFromIMDB(String id) {
//
//        return rest.getForEntity("http://www.omdbapi.com/?apikey="
//                + System.getenv("OMDBapiKey") +
//                "&i=" + id, ActorsDto.class).getBody();
//    }
}
