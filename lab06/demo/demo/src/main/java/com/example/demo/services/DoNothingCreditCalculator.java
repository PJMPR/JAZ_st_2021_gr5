package com.example.demo.services;

import com.example.demo.contract.CreditDto;
import com.example.demo.contract.InstallmentType;
import com.example.demo.model.TimetablePosition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoNothingCreditCalculator implements CreditCalculator{


    @Override
    public InstallmentType getInstallmentType() {
        return InstallmentType.CONSTANT;
    }

    @Override
    public List<TimetablePosition> getTimetable(CreditDto parameters) {
        return List.of(new TimetablePosition(), new TimetablePosition());
    }
}
