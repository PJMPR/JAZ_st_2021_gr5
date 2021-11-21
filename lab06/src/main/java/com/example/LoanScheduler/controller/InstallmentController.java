package com.example.LoanScheduler.controller;

import com.example.LoanScheduler.Calculator.InstallmentData;
import com.example.LoanScheduler.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InstallmentController {
    InstallmentService installmentService;

    @Autowired
    public InstallmentController(InstallmentService installmentService){
        this.installmentService = installmentService;
    }
    @PostMapping("/credit/calculations")
    public long saveInstallmentData(@RequestBody InstallmentData installmentData){
        return installmentService.insertData(installmentData);
    }
}
