package com.example.demo.controllers;

import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    CustomerService customerService;

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        Timestamp t = Timestamp.valueOf("2021-01-10 00:00:00");
        return ResponseEntity.ok(customerService.customerRepository.getById(id).getPayments().stream().map(x->x.getLastUpdate()).collect(Collectors.toList()));
    }

    @GetMapping
    @RequestMapping("/ranking/bySpentMoney")
    public ResponseEntity getTopMoneySpent(){
        return ResponseEntity.ok(customerService.getTopMoneySpent(10));
    }

    @GetMapping
    @RequestMapping("/ranking/bySpentMoney.jpg/{type}")
    public ResponseEntity getTopMoneySpentChart(@PathVariable("type") String type){
        return ResponseEntity.ok(customerService.getTopMoneySpentChart(10));
    }

    @GetMapping
    @RequestMapping("/ranking/byWatchedMovies")
    public ResponseEntity getTopWatchedMovies(){
        return ResponseEntity.ok(customerService.getTopWatchedMovies(10));
    }

    @GetMapping
    @RequestMapping("/ranking/byWatchedMovies.jpg/{type}")
    public ResponseEntity getTopWatchedMoviesChart(@PathVariable String type){
        return ResponseEntity.ok(customerService.getTopWatchedMoviesChart(10));
    }

    @GetMapping
    @RequestMapping("/activity/rentMoviesByMonth/{year}")
    public ResponseEntity getMoviesByMonth(@PathVariable int year){
        return ResponseEntity.ok(customerService.getMoviesByMonth(year));
    }


}