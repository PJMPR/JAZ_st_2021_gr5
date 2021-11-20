package com.example.demo.service;

import com.example.demo.repository.TimetableRepository;
import com.example.demo.timetable.Timetable;
import org.springframework.stereotype.Component;

@Component
public class TimetableService {
    public TimetableRepository timetableRepository;

    public Timetable findTimetableById(int id) {
        return timetableRepository.findById(id);
    }

    public void addTimetable() {
    }

}
