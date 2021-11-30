package com.example.demo.model;

public class rentMoviesByMonth {
    private int month;
    private int rentMovies;

    public rentMoviesByMonth(int month, int rentMovies) {
        this.month = month;
        this.rentMovies = rentMovies;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getRentMovies() {
        return rentMovies;
    }

    public void setRentMovies(int rentMovies) {
        this.rentMovies = rentMovies;
    }
}
