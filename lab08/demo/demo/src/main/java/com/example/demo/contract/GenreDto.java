package com.example.demo.contract;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenreDto {

    int id;
    String name;
}
