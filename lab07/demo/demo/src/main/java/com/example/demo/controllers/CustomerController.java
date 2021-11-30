package com.example.demo.controllers;

import com.example.demo.data.Payment;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.service.CustomerService;
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
        return ResponseEntity.ok(customerService.getById(id).getPayments().stream().map(Payment::getLastUpdate).collect(Collectors.toList()));
    }



    @GetMapping
    @RequestMapping("/ranking/bySpentMoney")
    public ResponseEntity getRankingByMoneySpent(){
        return ResponseEntity.ok(customerService.getRankingByMoneySpent());
    }

    @GetMapping
    @RequestMapping("/ranking/byWatchedMovies")
    public ResponseEntity getRankingByWatchedMovies(){
        return ResponseEntity.ok(customerService.getRankingByMoviesWatched());
    }
}
