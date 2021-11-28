package com.example.demo.controllers;

import com.example.demo.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rental")
public class RentalController {

    RentalRepository repository;

    @Autowired
    public RentalController(RentalRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "incomeByMonth")
    public ResponseEntity getIncomeByMonth(@RequestParam String year){
        return ResponseEntity.ok(repository.incomeByMonth(year));
    }

    @GetMapping(path = "income")
    public ResponseEntity getIncomeFromTo(@RequestParam String from, @RequestParam String to){
        return ResponseEntity.ok(repository.incomeByMonthFromTo(from, to));
    }
}
