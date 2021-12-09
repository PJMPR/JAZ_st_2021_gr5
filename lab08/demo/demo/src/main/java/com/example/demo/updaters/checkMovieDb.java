package com.example.demo.updaters;
import com.example.demo.contract.MovieFromMovieDb;
import com.example.demo.model.Film;
import com.example.demo.repositories.InsertFilmRepo;
import com.example.demo.services.TheMovieDbService;

import java.util.Collection;
import java.util.HashMap;

public class checkMovieDb {
    private TheMovieDbService service = new TheMovieDbService();
    private InsertFilmRepo filmRepo;

    public boolean isPresentInMovieDb(HashMap<Film,String> imdbIdsForFilms, Collection<Film> films, Collection<Film> removedFilms) {

        for (Film film : films) {
            MovieFromMovieDb movieFromMovieDb = service.getMovieFromMovieDb(imdbIdsForFilms.get(film));
            if (!movieFromMovieDb.isResponse()) {
                removedFilms.add(film);
                films.remove(film);
            }
        }

        for (Film film : removedFilms) {
            filmRepo.deleteFilm(film);
        }

        if (films.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
