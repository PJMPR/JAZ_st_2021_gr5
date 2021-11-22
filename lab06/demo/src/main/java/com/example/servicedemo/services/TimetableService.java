package com.example.servicedemo.services;

import com.example.servicedemo.Timetable.Timetable;
import com.example.servicedemo.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimetableService {
    TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository){
        this.timetableRepository = timetableRepository;
    }

    public int insertData(Timetable timetable){
        timetableRepository.save(timetable);
        return timetable.getID();
    }

    public Timetable getTimetable(int id){
        return timetableRepository.findByID(id);
    }

}
