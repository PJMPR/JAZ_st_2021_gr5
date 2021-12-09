package com.example.demo.updaters;
import com.example.demo.contract.MovieFromOmdb;
import com.example.demo.model.Film;
import com.example.demo.services.OmdbService;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class checkOmdb {
    private OmdbService service = new OmdbService();
    private String title;
    private String imdbId;

    public boolean isPresentInOmdb(HashMap<Film, String> imdbIdsForFilms, Collection<Film> films, Collection<Film> removedFilms) {

        /*for (Film film : films) {
            title = film.getTitle();
            MovieFromOmdb movieFromOmdb = service.getMovie(title);

            if (!movieFromOmdb.isResponse()) {
                films.remove(film);
                removedFilms.add(film);
            }
            imdbId = movieFromOmdb.getImdbId();
            imdbIdsForFilms.put(film, imdbId);
        }*/

        for (Iterator<Film> it = films.iterator(); it.hasNext();) {
            Film next = it.next();
            title = next.getTitle();
            MovieFromOmdb movieFromOmdb = service.getMovie(title);

            if (!movieFromOmdb.isResponse()) {
                it.remove();
                removedFilms.add(next);
            }
            imdbId = movieFromOmdb.getImdbId();
            imdbIdsForFilms.put(next, imdbId);
        }

        if(films.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
