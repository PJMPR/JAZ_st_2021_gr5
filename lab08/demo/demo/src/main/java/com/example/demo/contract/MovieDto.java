package com.example.demo.contract;

import com.example.demo.model.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieDto {

    private String title;
    private int id;
   @JsonProperty("runtime")
    private int runtime;
    @JsonProperty("overview")
    private String overview;
    private int year;
    private String rating;

    private List<Actor> actors;

    private String imdb_id;

    public MovieDto(String title, int id, int runtime, String overview, int year, String rating, List<Actor> actors, String imdb_id) {
        this.title = title;
        this.id = id;
        this.runtime = runtime;
        this.overview = overview;
        this.year = year;
        this.rating = rating;
        this.actors = actors;
        this.imdb_id = imdb_id;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void setOverview(String description) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getOverview() {
        return overview;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }
}
