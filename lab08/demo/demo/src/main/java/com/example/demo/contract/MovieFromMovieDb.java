package com.example.demo.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieFromMovieDb {
    int id;
    int runtime;
    String title;
    @JsonProperty("success")
    boolean response;

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
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
}
