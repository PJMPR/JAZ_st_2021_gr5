package com.example.demo.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieDto {

    private String title;
    @JsonProperty("overview")
    private String description;
    private String release_date;
    private String original_language;
    private List<GenreDto> genres;
    @JsonProperty("spoken_languages")
    private List<LanguagesDto> languages;
    private int runtime;
    private String imdb_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public List<LanguagesDto> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguagesDto> languages) {
        this.languages = languages;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }
}
