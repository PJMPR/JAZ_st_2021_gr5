package com.example.demo.data;

import java.math.BigDecimal;

public class CustomerStats {
    private int id;
    private String name;
    private String surname;
    private BigDecimal amountSpent;
    private int moviesWatched;

    public CustomerStats(int id, String name, String surname, BigDecimal amountSpent, int moviesWatched){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.amountSpent = amountSpent;
        this.moviesWatched = moviesWatched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public BigDecimal getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(BigDecimal amountSpent) {
        this.amountSpent = amountSpent;
    }

    public int getMoviesWatched() {
        return moviesWatched;
    }

    public void setMoviesWatched(int moviesWatched) {
        this.moviesWatched = moviesWatched;
    }
}
