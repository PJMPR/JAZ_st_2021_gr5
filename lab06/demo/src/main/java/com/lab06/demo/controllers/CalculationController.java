package com.lab06.demo.controllers;

import com.lab06.demo.calculation.Calculation;
import com.lab06.demo.repositories.TimetableRepository;
import com.lab06.demo.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/credit/calculations")
public class CalculationController {

    private final CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping
    public long registerNewCalculation(@RequestBody Calculation calculation){
        return calculationService.addNewCalculation(calculation);
    }
}
