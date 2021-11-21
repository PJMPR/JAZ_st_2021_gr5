package com.example.lab06.Service;

import com.example.lab06.Installment;
import com.example.lab06.Repository.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstallmentService {
    InstallmentRepository installmentRepository;

    @Autowired
    public InstallmentService(InstallmentRepository installmentRepository){
        this.installmentRepository = installmentRepository;
    }

    public void save(Installment installment) {
        installmentRepository.save(installment);
    }

    public Installment getTimetable(long id) {
        return  installmentRepository.findById(id);
    }
}
