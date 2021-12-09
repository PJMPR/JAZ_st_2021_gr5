package com.example.demo.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

}
