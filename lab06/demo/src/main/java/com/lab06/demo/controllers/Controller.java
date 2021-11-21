package com.lab06.demo.controllers;

import com.lab06.demo.entities.Calculation;
import com.lab06.demo.entities.Timetable;
import com.lab06.demo.services.CalculationService;
import com.lab06.demo.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/credit")
public class Controller {

    private final CalculationService calculationService;
    private final TimetableService timetableService;

    @Autowired
    public Controller(CalculationService calculationService, TimetableService timetableService) {
        this.calculationService = calculationService;
        this.timetableService = timetableService;
    }

    @PostMapping(path = "/calculations")
    public long registerNewCalculation(@RequestBody Calculation calculation){
        return calculationService.addNewCalculation(calculation);
    }

    @GetMapping(path = "/calculations")
    public Calculation getCalculation(@RequestParam long id){
        return calculationService.getCalculation(id);
    }

    @GetMapping(path = "/timetable")
    public List<Timetable> getTimetableJSON(@RequestParam long id){
        return timetableService.getTimetable(id);
    }
}
