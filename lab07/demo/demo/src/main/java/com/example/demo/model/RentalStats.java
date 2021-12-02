package com.example.demo.model;

public class RentalStats{
    private int month;
    private int rentValue;

    public RentalStats(int month, int rentMovies) {
        this.month = month;
        this.rentValue = rentMovies;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getRentValue() {
        return rentValue;
    }

    public void setRentValue(int rentValue) {
        this.rentValue = rentValue;
    }

}
