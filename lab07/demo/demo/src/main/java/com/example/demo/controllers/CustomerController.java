package com.example.demo.controllers;

import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers")
public class CustomerController {

    CustomerRepository repository;

    @Autowired
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "ranking/bySpendMoney")
    public ResponseEntity get10ClientsThatSpentMost() {
        return ResponseEntity.ok(repository.findTop10BySpentMost());
    }

    @GetMapping(path = "ranking/byWatchedMovies")
    public ResponseEntity get10ClientsThatWatchedMost() {
        return ResponseEntity.ok(repository.findTop10ByWatchedMost());
    }

    @RequestMapping(value = "activity/rentMoviesByMonth", params = "year")
    public ResponseEntity getRentMoviesByMonth(@RequestParam String year){
        return ResponseEntity.ok(repository.findRentMoviesByMonth(year));
    }

    @RequestMapping(value = "activity/rentMoviesByMonth", params = "customerid")
    public ResponseEntity getRentMoviesByMonthByCustomer(@RequestParam String customerid){
        return ResponseEntity.ok(repository.findRentMoviesByMonthByCustomer(customerid));
    }
}
