package com.example.demo.controllers;

import com.example.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rental")
public class RentalController {
    @Autowired
    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    RentalService rentalService;

    @GetMapping
    @RequestMapping("/incomeByMonth/{year}")
    public ResponseEntity getIncomeByMonth(@PathVariable int year){
        return ResponseEntity.ok(rentalService.getIncomeByMonth(year));
    }
}
