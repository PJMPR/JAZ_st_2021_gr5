package com.example.LoanScheduler.service;

import com.example.LoanScheduler.Loan.Installment;
import com.example.LoanScheduler.Loan.Timetable;
import com.example.LoanScheduler.calculator.InstallmentCalculator;
import com.example.LoanScheduler.repos.InstallmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InstallmentService {
    InstallmentRepository installmentRepository;
    InstallmentCalculator installmentCalculator;

    public InstallmentService(InstallmentRepository installmentRepository, InstallmentCalculator installmentCalculator){
        this.installmentRepository = installmentRepository;
        this.installmentCalculator = installmentCalculator;
    }


    public List<Installment> calculateInstallments(Timetable timetable){
        return installmentCalculator.calculateInstalments(timetable);
    }

    public void saveInstallments(Installment installment){
        installmentRepository.save(installment);
    }

}
