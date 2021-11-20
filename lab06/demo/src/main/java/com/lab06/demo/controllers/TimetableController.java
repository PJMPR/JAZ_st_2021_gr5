package com.lab06.demo.controllers;

import com.lab06.demo.repositories.TimetableRepository;
import com.lab06.demo.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "credit/timetable")
public class TimetableController {
    private final TimetableService timetableService;
    private final TimetableRepository timetableRepository;

    @Autowired
    public TimetableController(TimetableService timetableService, TimetableRepository timetableRepository) {
        this.timetableService = timetableService;
        this.timetableRepository = timetableRepository;
    }
}
