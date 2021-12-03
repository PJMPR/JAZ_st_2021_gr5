package com.example.demo.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OMBDto {

    @JsonProperty("Title")
    String title;
    @JsonProperty("Year")
    String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
