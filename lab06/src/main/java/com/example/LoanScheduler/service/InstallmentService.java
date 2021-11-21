package com.example.LoanScheduler.service;

import com.example.LoanScheduler.Calculator.InstallmentData;
import com.example.LoanScheduler.repos.InstallmentRepository;
import org.springframework.stereotype.Component;

@Component
public class InstallmentService {
    InstallmentRepository installmentRepository;

    public InstallmentService(InstallmentRepository installmentRepository){
        this.installmentRepository = installmentRepository;
    }

    public long insertData(InstallmentData installmentData){
        installmentRepository.save(installmentData);
        return installmentData.getId();
    }
}
