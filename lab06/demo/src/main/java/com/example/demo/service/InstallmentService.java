package com.example.demo.service;

import com.example.demo.repository.InstallmentRepository;
import com.example.demo.timetable.Installment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstallmentService {
    InstallmentRepository installmentRepository;

    @Autowired
    public InstallmentService(InstallmentRepository installmentRepository) {
        this.installmentRepository = installmentRepository;
    }

    public Installment findTimetableById(int id) {
        return installmentRepository.findById(id);
    }

    public void addInstallment(Installment installment) {
        installmentRepository.save(installment);
    }
}
