package com.example.LoanScheduler.controller;

import com.example.LoanScheduler.installment.Installment;
import com.example.LoanScheduler.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimetableController {
    TimetableService timetableService;

    @Autowired
    public TimetableController(TimetableService timetableService){
        this.timetableService = timetableService;
    }

    @GetMapping("credit/timetable/{id}")
    public Installment getTimetable(@PathVariable Long id){
        return timetableService.getTimetable(id);
    }
}
