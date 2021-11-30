package com.example.demo.model;

import java.math.BigDecimal;

public class customerWatchedMovies {
    private int customerId;
    private String firstName;
    private String lastName;
    private Integer watchedMovies;

    public customerWatchedMovies(int customerId, String firstName, String lastName, Integer watchedMovies) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.watchedMovies = watchedMovies;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(Integer watchedMovies) {
        this.watchedMovies = watchedMovies;
    }
}
