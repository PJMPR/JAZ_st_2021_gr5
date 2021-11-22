package com.example.lab06;

import com.example.lab06.Service.InstallmentService;
import com.example.lab06.Service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimetableController {
    TimetableService timetableService;
    InstallmentService installmentService;
    InstallmentCalculator calculator = new InstallmentCalculator();

    @Autowired
    public TimetableController(TimetableService timetableService, InstallmentService installmentService){
        this.timetableService = timetableService;
        this.installmentService = installmentService;
    }

    @PostMapping("/credit/calculations")
    public long AddTimetable(@RequestBody Timetable timetable){
        installmentService.save(calculator.calculate(timetable));
        return timetableService.save(timetable);
    }

    @GetMapping("/credit/timetable?{id}")
    public Timetable getTimetable(@PathVariable long id){
        return timetableService.getTimetable(id);
    }


}
