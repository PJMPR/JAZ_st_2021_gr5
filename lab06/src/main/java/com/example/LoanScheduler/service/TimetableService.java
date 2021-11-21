package com.example.LoanScheduler.service;

import com.example.LoanScheduler.installment.Installment;
import com.example.LoanScheduler.repos.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimetableService {
    TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository){
        this.timetableRepository = timetableRepository;
    }

    public Installment getTimetable(long id){
        return timetableRepository.findById(id);
    }
}
