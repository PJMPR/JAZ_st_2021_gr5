package com.example.demo.repositories;

import com.example.demo.data.Customer;
import com.example.demo.data.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
