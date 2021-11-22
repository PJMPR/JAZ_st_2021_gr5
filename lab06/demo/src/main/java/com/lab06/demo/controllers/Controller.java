package com.lab06.demo.controllers;

import com.lab06.demo.entities.Calculation;
import com.lab06.demo.entities.Timetable;
import com.lab06.demo.services.CSVService;
import com.lab06.demo.services.CalculationService;
import com.lab06.demo.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/credit")
public class Controller {

    private final CalculationService calculationService;
    private final TimetableService timetableService;
    private final CSVService csvService;

    @Autowired
    public Controller(CalculationService calculationService, TimetableService timetableService, CSVService csvService) {
        this.calculationService = calculationService;
        this.timetableService = timetableService;
        this.csvService = csvService;
    }

    @PostMapping(path = "/calculations")
    public long registerNewCalculation(@RequestBody Calculation calculation) {
        return calculationService.addNewCalculation(calculation);
    }

    @GetMapping(path = "/calculations")
    public Calculation getCalculation(@RequestParam long id) {
        return calculationService.getCalculation(id);
    }

    @GetMapping(path = "/timetable", produces = "application/json")
    public List<Timetable> getTimetableJSON(@RequestParam long id) {
        return timetableService.getTimetable(id);
    }

    @GetMapping(path = "/timetable/file")
    public ResponseEntity<Resource> getTimetableCSV(@RequestParam long id, @RequestParam String file) {
        if (file.equals("CSV")) {
            String filename = "file.csv";
            InputStreamResource inputStreamResource = new InputStreamResource(csvService.load(id));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(inputStreamResource);
        } else {
            return null;
        }
    }
}
