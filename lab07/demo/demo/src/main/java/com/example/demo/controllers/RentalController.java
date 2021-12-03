package com.example.demo.controllers;

import com.example.demo.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
        return ResponseEntity.ok(rentalService.getIncomeByYear(year));
    }

    @GetMapping
    @RequestMapping("/income/{from}/{to}")
    public ResponseEntity getIncomeFromTo(@PathVariable String from, @PathVariable String to){
        return ResponseEntity.ok(rentalService.gerIncomeFromTo(from, to));
    }

    @GetMapping
    @RequestMapping("/incomeByMonth.jpg/{chart}/{year}")
    public ResponseEntity getIncomeByMonth(@PathVariable int year, @PathVariable String chart) throws IOException {
        switch (chart) {
            case "linear":
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(rentalService.generateRentalLinearChart(
                                "Income by month",
                                "Months",
                                "Income",
                                rentalService.getIncomeByYear(year)));
            case "bar":
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(rentalService.generateRentalBarChart(
                                "Income by month",
                                "Months",
                                "Income",
                                rentalService.getIncomeByYear(year)));
            case "pie":
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(rentalService.generateRentalPieChart(
                                "Income by month",
                                rentalService.getIncomeByYear(year)));
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }
}