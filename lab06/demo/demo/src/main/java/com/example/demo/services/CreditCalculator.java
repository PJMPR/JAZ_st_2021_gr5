package com.example.demo.services;

import com.example.demo.contract.CreditDto;
import com.example.demo.contract.InstallmentType;
import com.example.demo.model.TimetablePosition;

import java.util.List;

public interface CreditCalculator {

    InstallmentType getInstallmentType();
    List<TimetablePosition> getTimetable(CreditDto parameters);
}
