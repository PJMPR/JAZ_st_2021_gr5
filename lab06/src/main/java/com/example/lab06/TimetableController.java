package com.example.lab06;

import com.example.lab06.Service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimetableController {
    TimetableService timetableService;

    @Autowired
    public TimetableController(TimetableService timetableService){
        this.timetableService = timetableService;
    }

    @PostMapping("/credit/calculations")
    public long AddTimetable(@RequestBody Timetable timetable){
        return timetableService.save(timetable);
    }

    @GetMapping("/credit/timetable?{id}")
    public Timetable getTimetable(@PathVariable long id){
        return getTimetable(id);
    }


}
