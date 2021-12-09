package com.example.demo.updaters;
import com.example.demo.contract.MovieFromOmdb;
import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import com.example.demo.model.FilmActor;
import com.example.demo.repositories.ActorInsertRepo;
import com.example.demo.services.OmdbService;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class updateActors {
    private List<String> actors;
    private String actorName;
    private String[] name;
    private ActorInsertRepo actorInsertRepo;
    private String title;
    private OmdbService omdbService;

    public void updateActors(Collection<Film> films) {

        for (Film film : films) {
            title = film.getTitle();
            MovieFromOmdb movieFromOmdb = omdbService.getMovie(title);
            actors = Arrays.asList(movieFromOmdb.getActors().split(","));

            for (FilmActor filmActor : film.getFilmActors()) {
                Actor actor = filmActor.getActor();
                actorName = actor.getFirstName() + " " + actor.getLastName();

                if (!actors.contains(actorName)) {
                    actorInsertRepo.deleteFilmActor(actor, film);
                    actorInsertRepo.deleteActor(actor);
                } else {
                    actors.remove(actorName);
                }
            }

            if(!actors.isEmpty()) {

                for (String actor1 : actors) {
                    name = actor1.split(" ");
                    Actor actor = new Actor();
                    actor.setFirstName(name[0]);
                    actor.setLastName(name[1]);
                    actorInsertRepo.insertActor(actor);
                    actorInsertRepo.insertFilmActor(actor, film);
                }
            }
        }
    }

}
