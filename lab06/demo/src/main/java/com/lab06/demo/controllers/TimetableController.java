package com.lab06.demo.controllers;

import com.lab06.demo.repositories.TimetableRepository;
import com.lab06.demo.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "credit/timetable")
public class TimetableController {
    private final CalculationService calculationService;
    private final TimetableRepository timetableRepository;

    @Autowired
    public TimetableController(CalculationService calculationService, TimetableRepository timetableRepository) {
        this.calculationService = calculationService;
        this.timetableRepository = timetableRepository;
    }
}
