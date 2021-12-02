package com.example.lab06;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimetableService {
    TimetableRepository repo;

    @Autowired
    public TimetableService(TimetableRepository repo) {
        repo.save(new Timetable());
        this.repo = repo;
    }

    public int save(Timetable timetable) {
        return repo.save(timetable).getId();
    }

    public Timetable getTimetable(Integer id) {
        return repo.findById(id);
    }
}
