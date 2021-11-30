package com.example.demo.databuilders.customer;

import com.example.demo.repositories.CustomerRepository;

public abstract class BuildCustomer {
    CustomerRepository repository;

    public BuildCustomer(CustomerRepository repository) {
        this.repository = repository;
    }
}
