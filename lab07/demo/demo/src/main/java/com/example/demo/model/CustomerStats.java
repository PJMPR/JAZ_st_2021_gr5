package com.example.demo.model;

import java.math.BigDecimal;

public class CustomerStats {
    private int customerId;
    private String name;
    private String surname;
    private BigDecimal moneySpent;
    private int watchedMovies;

    public CustomerStats(int id, String name, String surname, BigDecimal amountSpent, int moviesWatched){
        this.customerId = id;
        this.name = name;
        this.surname = surname;
        this.moneySpent = amountSpent;
        this.watchedMovies = moviesWatched;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(BigDecimal moneySpent) {
        this.moneySpent = moneySpent;
    }

    public int getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(int watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

}