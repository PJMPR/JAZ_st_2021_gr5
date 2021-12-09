package com.example.demo.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMDbDto {

    @JsonProperty("Rated")
    private String rating;
    @JsonProperty("Actors")
    private String actors;
}
