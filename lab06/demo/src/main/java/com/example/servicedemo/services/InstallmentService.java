package com.example.servicedemo.services;

import com.example.servicedemo.Calculator;
import com.example.servicedemo.Timetable.Installment;
import com.example.servicedemo.Timetable.Timetable;
import com.example.servicedemo.repository.InstallmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InstallmentService {
    InstallmentRepository installmentRepository;
    Calculator calculator;

    public List<Installment> calculate(Timetable timetable){
        return calculator.calculate(timetable);
    }

    public void saveInstallments(Installment installment){
        installmentRepository.save(installment);
    }

}
