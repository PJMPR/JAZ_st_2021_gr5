package com.example.demo.services;
import com.example.demo.model.Film;
import com.example.demo.repositories.FilmRepo;
import com.example.demo.updaters.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashMap;

@Service
public class Updater {
    private FilmRepo repo;
    private Collection<Film> removedFilms;
    private Collection<Film> films;
    private HashMap<Film, String> imdbIds = new HashMap<>();
    private com.example.demo.updaters.checkMovieDb checkMovieDb = new checkMovieDb();
    private com.example.demo.updaters.checkOmdb checkOmdb = new checkOmdb();
    private com.example.demo.updaters.updateActors updateActors = new updateActors();
    private com.example.demo.updaters.updateCategories updateCategories = new updateCategories();
    private com.example.demo.updaters.updateFilmData updateFilmData = new updateFilmData();

    @Autowired
    public Updater(FilmRepo repo, Collection<Film> removedFilms, Collection<Film> films) {
        this.repo = repo;
        this.removedFilms = removedFilms;
        this.films = films;
    }

    public Updater() {}

    public boolean updateDb() {
        films = repo.getAllMovies();
        if (checkOmdb.isPresentInOmdb(imdbIds, films, removedFilms)) {
            if (checkMovieDb.isPresentInMovieDb(imdbIds, films, removedFilms)) {
                updateFilmData.updateFilms(imdbIds, films);
                updateActors.updateActors(films);
                updateCategories.updateCategories(films);

                return true;
            }
        }
        return false;
    }
}
