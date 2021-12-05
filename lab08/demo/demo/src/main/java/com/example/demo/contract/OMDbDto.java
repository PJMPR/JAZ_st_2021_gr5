package com.example.demo.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OMDbDto {

    @JsonProperty("Rated")
    private String rating;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Language")
    private String languages;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
