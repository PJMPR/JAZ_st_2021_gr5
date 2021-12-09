package com.example.demo.updaters;
import com.example.demo.contract.MovieFromMovieDb;
import com.example.demo.contract.MovieFromOmdb;
import com.example.demo.model.Film;
import com.example.demo.model.Language;
import com.example.demo.repositories.FilmRepo;
import com.example.demo.repositories.LanguageRepo;
import com.example.demo.services.OmdbService;
import com.example.demo.services.TheMovieDbService;
import java.util.Collection;
import java.util.HashMap;

public class updateFilmData {
    private TheMovieDbService movieDbService = new TheMovieDbService();
    private OmdbService omdbService = new OmdbService();
    private String title;
    private String imdbId;
    private String description;
    private String language;
    private int length;
    private int languageId;
    private LanguageRepo languageRepo;
    private FilmRepo filmRepo;


    public void updateFilms(HashMap<Film,String> imdbIdsForFilms, Collection<Film> films) {

        for (Film film : films) {
            title = film.getTitle();
            imdbId = imdbIdsForFilms.get(film);
            MovieFromMovieDb movieFromMovieDb = movieDbService.getMovieFromMovieDb(imdbId);
            MovieFromOmdb movieFromOmdb = omdbService.getMovie(title);
            description = movieFromOmdb.getDescription();
            language = movieFromOmdb.getLanguage();
            length = movieFromMovieDb.getRuntime();

            if (!film.getDescription().equals(description)) {
                filmRepo.updateFilmDescription(description, title);
            }
            if (!film.getLanguage().getName().equals(language)) {
                Collection<Language> languageArray = languageRepo.getLanguageByLanguageName(language);
                languageArray.stream().forEach(language1 -> languageId = language1.getLanguageId());
                filmRepo.updateFilmLanguage(languageId, title);
            }
            if (film.getLength() != length) {
                filmRepo.updateFilmRuntime(length, title);
            }
        }

    }
}
