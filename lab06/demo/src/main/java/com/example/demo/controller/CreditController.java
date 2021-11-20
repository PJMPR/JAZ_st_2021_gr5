package com.example.demo.controller;

import com.example.demo.creditParameters.CreditParameters;
import com.example.demo.service.TimetableService;
import com.example.demo.timetable.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreditController {
    TimetableService timetableService;

    @Autowired
    public CreditController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @PostMapping("/credit/calculations")
    public int addCredit(@RequestBody CreditParameters creditParameters){
        timetableService.addTimetable();

        return 1;
    }
    @GetMapping("/credit/timetable{id}")
    public Timetable getTimetable(@PathVariable int  id){
        return  timetableService.findTimetableById(id);
    }
}
