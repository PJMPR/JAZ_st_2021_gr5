package com.example.demo.service;

import com.example.demo.Quartz.job.UpdateDB;
import com.example.demo.Quartz.schedule.ScheduleInfo;
import com.example.demo.contract.IMDBMovieDto;
import com.example.demo.contract.MovieDto;
import com.example.demo.contract.SystemStatus;
import com.example.demo.model.Actor;
import com.example.demo.repository.ActorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
public class FilmService {
    RestTemplate rest;
    ActorRepository actorRepository;

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(FilmService.class));
    FileHandler fh;
    private final SystemStatus systemStatus;

    private final SchedulerService scheduler;

    @Autowired
    public FilmService(RestTemplate rest, ActorRepository actorRepository, SystemStatus systemStatusInfo, SchedulerService scheduler) {
        try {
            fh = new FileHandler("demo/src/main/java/com/example/demo/Log.txt", true);
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.info("Logger started");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.rest = rest;
        this.actorRepository = actorRepository;
        this.systemStatus = systemStatusInfo;
        this.scheduler = scheduler;
    }

    public void runDatabaseChecker(){
        final ScheduleInfo info = new ScheduleInfo();
        info.setRunForever(false);
        info.setRepeatIntervalHs(24);
        info.setStartHour(3);

        scheduler.schedule(UpdateDB.class, info);
    }

    public MovieDto getMoveInfo(int id) {
        var movie = rest.getForEntity("https://api.themoviedb.org/3/movie/" +
                id +
                "?api_key=" + System.getenv("TMDBapiKey"), MovieDto.class).getBody();

        if (movie != null) {
            var movieIMDB = getMovieInfoFromIMDB(movie.getImdb_id());
            BeanUtils.copyProperties(movieIMDB, movie);
            List<Actor> actors = new ArrayList<>();
            String[] names = movieIMDB.getActors().split(",");
            for (String s : names) {
                int actorId = checkIfActorExist(s);
                if (actorId == 0) {
                    String[] parts = s.strip().split(" ");
                    actors.add(new Actor(parts[0], parts[1]));
                } else {
                    actors.add(actorRepository.findById(actorId).get());
                }
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

    public int checkIfActorExist(String name) {

        String firstname = name.strip().split(" ")[0];
        String lastname = name.strip().split(" ")[1];
        boolean isInDB = actorRepository.findAll().stream().anyMatch(actor -> Objects.equals(actor.getFirstName(), firstname)
                && Objects.equals(actor.getLastName(), lastname));

        if (isInDB) {
            return actorRepository.findAll().stream().filter(actor -> Objects.equals(actor.getFirstName(), firstname)
                    && Objects.equals(actor.getLastName(), lastname)).findFirst().get().getActorId();
        } else return 0;

    }

//    public ActorsDto getActorsInfoFromIMDB(String id) {
//
//        return rest.getForEntity("http://www.omdbapi.com/?apikey="
//                + System.getenv("OMDBapiKey") +
//                "&i=" + id, ActorsDto.class).getBody();
//    }


//    public MovieDto useWrapper(int id){
//        TmdbMovies movies = new TmdbApi(System.getenv("TMDBapiKey")).getMovies();
//        MovieDb movie = movies.getMovie(id,"en", TmdbMovies.MovieMethod.credits);
//        List<Actor> actors = new ArrayList<>();
//
//        movie.getCast().forEach(personCast -> {
//            String[] name = personCast.getName().split(" ");
//            actors.add(new Actor(personCast.getId(), name[0],name[1]));
//        });
//        MovieDto movieDto = new MovieDto(movie.getTitle(),
//                movie.getId(),
//                movie.getRuntime(),
//                movie.getOverview(),
//                Integer.parseInt(movie.getReleaseDate().split("-")[0]),
//                "",
//                actors,
//                movie.getImdbID());
//
//        return movieDto;
//    }

    public SystemStatus getSystemStatusInfo() {
        return systemStatus;
    }

    public String reloadData() {
        return "reloading...";
    }

    public String reloadDataByYear(int year) {
        return "reloadnig...";
    }
}
