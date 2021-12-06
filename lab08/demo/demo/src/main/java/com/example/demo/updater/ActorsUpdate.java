package com.example.demo.updater;

import com.example.demo.contract.MovieDto;
import com.example.demo.contract.OMDbDto;
import com.example.demo.model.Actor;
import com.example.demo.repositories.ActorsRepo;
import com.example.demo.repositories.projections.IActors;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ActorsUpdate implements Chain {
    private Chain nextInChain;
    private ActorsRepo repo;

    public ActorsUpdate(ActorsRepo repo) {
        this.repo = repo;
    }

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void query(MovieDto movieDto, OMDbDto omDbDto) {

        List<String> actorsList = Arrays.stream(omDbDto.getActors().split(", ")).toList();

        for (String actor : actorsList) {
            List<String> dbList = repo.getAllActors().stream().map(IActors::getFirstName).collect(Collectors.toList());
            List<String> actorName = new ArrayList<>(Arrays.asList(actor.split(" ")));

            if (actorName.size() == 1)
                actorName.add(null);

            if (!actorName.get(0).equals("N/A") && !dbList.contains(actorName.get(0)) && !dbList.contains(actorName.get(1))) {

                long time = new Date().getTime();
                Actor a = new Actor();
                a.setActorId(dbList.size() + 1);
                a.setFirstName(actorName.get(0));
                a.setLastUpdate(new Timestamp(time));

                if (actorName.get(1) != null) {
                    if (actorName.size() == 3) {
                        a.setLastName(actorName.get(1) + " " + actorName.get(2));
                    } else {
                        a.setLastName(actorName.get(1));
                    }
                } else {
                    a.setLastName("");
                }

                repo.save(a);
            }
        }

//            nextInChain.query(movieDto, omDbDto);
    }
}

