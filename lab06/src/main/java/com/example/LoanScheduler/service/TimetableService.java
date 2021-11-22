package com.example.LoanScheduler.service;

import com.example.LoanScheduler.Loan.Installment;
import com.example.LoanScheduler.Loan.Timetable;
import com.example.LoanScheduler.repos.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimetableService {
    TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public int insertData(Timetable timetable) {
        timetableRepository.save(timetable);
        return timetable.getId();
    }

    public Timetable getTimetable(int id) {
        return timetableRepository.findById(id);
    }

}
