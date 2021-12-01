package com.example.demo.data;

public class CustomerStats {
    private Customer customer;
    public double amountSpent;
    public int moviesWatched;

    public CustomerStats(Customer customer, Double amountSpent, int moviesWatched){
        this.customer = customer;
        this.amountSpent = amountSpent;
        this.moviesWatched = moviesWatched;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
