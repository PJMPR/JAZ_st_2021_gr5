package com.example.lab06.Service;

import com.example.lab06.Repository.TimetableRepository;
import com.example.lab06.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimetableService {
    TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository){
        timetableRepository.save(new Timetable());
        timetableRepository.save(new Timetable());
        this.timetableRepository = timetableRepository;
    }

    public long save(Timetable timetable) {
        return timetableRepository.save(timetable).getId();
    }

    public Timetable getTimetable(long id) {
        return  timetableRepository.findById(id);
    }
}
