package com.example.demo.updater;

import com.example.demo.contract.MovieDto;
import com.example.demo.contract.OMDbDto;
import com.example.demo.model.Actor;
import com.example.demo.model.FilmActor;
import com.example.demo.repositories.ActorsRepo;
import com.example.demo.repositories.FilmActorsRepo;
import com.example.demo.repositories.FilmRepo;
import com.example.demo.repositories.projections.IActors;
import com.example.demo.repositories.projections.IFilm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ActorsUpdate implements Chain {
    private Chain nextInChain;
    private ActorsRepo repo;
    private FilmRepo filmRepo;
    private FilmActorsRepo filmActorsRepo;

    public ActorsUpdate(ActorsRepo repo, FilmActorsRepo filmActorsRepo, FilmRepo filmRepo) {
        this.repo = repo;
        this.filmRepo = filmRepo;
        this.filmActorsRepo = filmActorsRepo;
    }

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void query(MovieDto movieDto, OMDbDto omDbDto) {
        List<String> filmTitle = filmRepo.getAllFilms().stream().map(IFilm::getTitle).collect(Collectors.toList());
        List<String> actorsList = Arrays.stream(omDbDto.getActors().split(", ")).toList();

        for (String actor : actorsList) {
            List<String> dbListFirstName = repo.getAllActors().stream().map(IActors::getFirstName).collect(Collectors.toList());
            List<String> dbListLastName = repo.getAllActors().stream().map(IActors::getLastName).collect(Collectors.toList());
            List<String> actorFirstAndLastName = new ArrayList<>(Arrays.asList(actor.split(" ")));
            long time = new Date().getTime();

            if (actorFirstAndLastName.size() == 1)
                actorFirstAndLastName.add(null);

            if (!actorFirstAndLastName.get(0).equals("N/A") && !dbListFirstName.contains(actorFirstAndLastName.get(0)) && !dbListLastName.contains(actorFirstAndLastName.get(1))) {
                Actor a = new Actor();
                a.setActorId(dbListFirstName.size() + 1);
                a.setFirstName(actorFirstAndLastName.get(0));
                a.setLastUpdate(new Timestamp(time));

                if (actorFirstAndLastName.get(1) != null) {
                    if (actorFirstAndLastName.size() == 3) {
                        a.setLastName(actorFirstAndLastName.get(1) + " " + actorFirstAndLastName.get(2));
                    } else {
                        a.setLastName(actorFirstAndLastName.get(1));
                    }
                } else {
                    a.setLastName("");
                }

                repo.save(a);
            }
            dbListFirstName = repo.getAllActors().stream().map(IActors::getFirstName).collect(Collectors.toList());

            if (!actorFirstAndLastName.get(0).equals("N/A")) {
                FilmActor filmActor = new FilmActor();
                filmActor.setFilmId(filmTitle.indexOf(movieDto.getTitle()) + 1);
                filmActor.setActorId(dbListFirstName.indexOf(actorFirstAndLastName.get(0)) + 1);
                filmActor.setLastUpdate(new Timestamp(time));

                filmActorsRepo.save(filmActor);
            }
        }
    }
}

