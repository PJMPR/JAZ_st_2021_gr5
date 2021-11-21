package com.example.demo.service;

import com.example.demo.repository.TimetableRepository;
import com.example.demo.timetable.Installment;
import com.example.demo.timetable.InstallmentCalculator;
import com.example.demo.timetable.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimetableService {
    public TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public Timetable findTimetableById(int id) {
        return timetableRepository.findById(id);
    }

    public void addTimetable(Timetable timetable) {
        timetableRepository.save(timetable);
    }

    public List<Installment> calculateInstalments(Timetable timetable){
        InstallmentCalculator installmentCalculator = new InstallmentCalculator();
       return installmentCalculator.calculateInstalments(timetable);
    }

}
