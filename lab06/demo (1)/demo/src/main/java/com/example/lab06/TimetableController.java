package com.example.lab06;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimetableController {
    TimetableService ttService;
    InstallmentService inService;
    Calculator calculator = new Calculator();

    @Autowired
    public TimetableController(TimetableService ttService, InstallmentService inService) {
        this.ttService = ttService;
        this.inService = inService;
    }

    @PostMapping("credit/calculations")
    public int AddTimetable(@RequestBody Timetable timetable) {
        inService.save(calculator.calc(timetable));
        return ttService.save(timetable);
    }

    @GetMapping("/credit/timetable?{id}")
    public Timetable getTimetable(@PathVariable int id) {
        return ttService.getTimetable(id);
    }
}
