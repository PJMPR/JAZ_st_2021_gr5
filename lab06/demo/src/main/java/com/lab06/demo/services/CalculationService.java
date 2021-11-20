package com.lab06.demo.services;

import com.lab06.demo.calculation.Calculation;
import com.lab06.demo.repositories.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {
    private final CalculationRepository calculationRepository;

    @Autowired
    public CalculationService(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    public long addNewCalculation(Calculation calculation) {
        calculationRepository.save(calculation);
        return calculation.getId();
    }
}
