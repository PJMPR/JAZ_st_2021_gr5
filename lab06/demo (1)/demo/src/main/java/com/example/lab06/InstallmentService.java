package com.example.lab06;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InstallmentService {
    InstallmentRepository repo;

    @Autowired
    public InstallmentService(InstallmentRepository repo) {
        this.repo = repo;
    }

    public void save(ArrayList<Installment> listOfInstallments) {
        repo.saveAll(listOfInstallments);
    }

    public Installment getInstallment(Integer id) {
        return repo.findById(id);
    }
}
