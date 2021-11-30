package com.example.demo.databuilders.rental;

import com.example.demo.repositories.RentalRepository;

public abstract class BuildRental {
    RentalRepository repository;

    public BuildRental(RentalRepository repository) {
        this.repository = repository;
    }
}
