package com.example.demo.repositories;

import com.example.demo.contracts.FilmDto;
import com.example.demo.contracts.LanguageDto;
import com.example.demo.exception.exceptions.FilmNotFoundException;
import com.example.demo.exception.exceptions.NoSuchPageException;
import com.example.demo.model.Film;
import com.example.demo.model.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FilmsRepository {

    private final EntityManager entityManager;
    Calendar timestamp = Calendar.getInstance();


    private FilmDto createFilmDto(Film film) {
        return FilmDto.builder()
                .id(film.getFilmId())
                .title(film.getTitle())
                .releaseYear(film.getReleaseYear())
                .language(LanguageDto.builder()
                        .id(film.getLanguage().getLanguageId())
                        .name(film.getLanguage().getName())
                        .build())
                .rentalDuration(new BigDecimal(film.getRentalDuration()))
                .rentalRate(film.getRentalRate())
                .replacementCosts(film.getReplacementCost())
                .build();
    }

//    public List<FilmDto> getAllFilms(){
//        return entityManager.createQuery("select film from Film film",Film.class)
//                .getResultList()
//                .stream()
//                .map(this::createFilmDto)
//                .collect(Collectors.toList());
//    }
    private String appendToQuery(FilmDto film){
        String str = "";
        if(film.getId() != null ){
            str+="film_id="+film.getId().toString()+" and ";
        }
        if(film.getTitle() != null && !Objects.equals(film.getTitle(), "")){
            str+="title='"+film.getTitle()+ "' and ";
        }
        if(film.getReleaseYear() != null){
            str+="release_year="+film.getReleaseYear().toString()+" and ";
        }
        if(film.getRentalDuration() != null){
            str+="rental_duration="+film.getRentalDuration().toString()+" and ";
        }
        if(film.getRentalRate() != null){
            str+="rental_rate="+film.getRentalRate().toString()+" and ";
        }
        if(film.getReplacementCosts() != null){
            str+="replacement_cost="+film.getReplacementCosts().toString()+" and ";
        }
        if(film.getLanguage().getId() != null && film.getLanguage().getId() !=7){
            str+="language_id="+film.getLanguage().getId().toString()+" and ";
        }

        if(!str.equals("")){
            return "where "+str+"true";
        }else return str;
    }

    public List<FilmDto> getFilmsByPage(int page, int size,FilmDto film) {
            String query="Select * From film "+appendToQuery(film);

        try {
            return (List<FilmDto>) entityManager.createNativeQuery(query+
                    " limit "
                    +size+
                    " offset "
                    +(page-1)*size,Film.class).getResultStream().map(f -> createFilmDto((Film) f)).collect(Collectors.toList());
//            return entityManager.createQuery(
//                            query, Film.class)
//                    .setFirstResult((page - 1) * size)
//                    .setMaxResults(size)
//                    .getResultList()
//                    .stream()
//                    .map(this::createFilmDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new NoSuchPageException();
        }
    }

    /*
        id jest generowane automatycznie, dlatego nie uwzglednialem go przy query
        Front przesyła id zawsze ustawione na 0 obojętnie co wpiszemy na stronie,

     */
    @Transactional
    public HttpStatus createFilm(FilmDto newFilm) {
        try {
            entityManager.joinTransaction();
            entityManager.createNativeQuery("INSERT INTO Film " +
                            "(title,release_year,rental_duration,rental_rate,replacement_cost,last_update,language_id)" +
                            "VALUES (?,?,?,?,?,?,?)")
                    .setParameter(1, newFilm.getTitle())
                    .setParameter(2, newFilm.getReleaseYear())
                    .setParameter(3, newFilm.getRentalDuration())
                    .setParameter(4, newFilm.getRentalRate())
                    .setParameter(5, newFilm.getReplacementCosts())
                    .setParameter(6, Timestamp.from(timestamp.getTime().toInstant()))
                    .setParameter(7, newFilm.getLanguage().getId())
                    .executeUpdate();
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.I_AM_A_TEAPOT;
        }
    }

    @Transactional
    public HttpStatus deleteFilmById(int id) {
        try {
            entityManager.joinTransaction();
            entityManager.createQuery("DELETE from Film f where f.filmId=:id")
                    .setParameter("id", id).executeUpdate();
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new FilmNotFoundException();
        }
    }

    @Transactional
    public HttpStatus updateFilm(FilmDto film) {
        try {
            entityManager.joinTransaction();
            entityManager.createQuery("update Film f set " +
                            "f.title=:title," +
                            "f.language.languageId=:languageId," +
                            "f.releaseYear=:releaseYear," +
                            "f.rentalDuration=:rentalDuration," +
                            "f.rentalRate=:rentalRate," +
                            "f.replacementCost=:replacementCost," +
                            "f.lastUpdate=:lastUpdate " +
                            "where f.filmId=:id")
                    .setParameter("title", film.getTitle())
                    .setParameter("languageId", film.getLanguage().getId())
                    .setParameter("releaseYear", film.getReleaseYear())
                    .setParameter("rentalDuration", film.getRentalDuration().intValue())
                    .setParameter("rentalRate", film.getRentalRate())
                    .setParameter("replacementCost", film.getReplacementCosts())
                    .setParameter("lastUpdate", Timestamp.from(timestamp.getTime().toInstant()))
                    .setParameter("id", film.getId())
                    .executeUpdate();
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new FilmNotFoundException();
        }
    }
}
