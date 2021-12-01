package com.example.demo.data;

public class CustomerData {
    private int id;
    private String firstName;
    private String lastName;
    public double amountSpent;
    public int moviesWatched;

    public CustomerData(int id, String firstName, String lastName, double amountSpent, int moviesWatched) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.amountSpent = amountSpent;
        this.moviesWatched = moviesWatched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public int getMoviesWatched() {
        return moviesWatched;
    }

    public void setMoviesWatched(int moviesWatched) {
        this.moviesWatched = moviesWatched;
    }
}
