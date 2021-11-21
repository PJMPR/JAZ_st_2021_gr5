package com.example.demo.controller;

import com.example.demo.creditParameters.CreditParameters;
import com.example.demo.service.InstallmentService;
import com.example.demo.service.TimetableService;
import com.example.demo.timetable.Installment;
import com.example.demo.timetable.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreditController {
    TimetableService timetableService;
    InstallmentService installmentService;

    @Autowired
    public CreditController(TimetableService timetableService, InstallmentService installmentService) {
        this.timetableService = timetableService;
        this.installmentService = installmentService;
    }




    @PostMapping("/credit/calculations")
    public int addCredit(@RequestBody CreditParameters creditParameters){
        Timetable timetable = new Timetable(creditParameters);

        timetableService.addTimetable(timetable);
        List<Installment> installmentList = timetableService.calculateInstalments(timetable);
        installmentList.forEach(installment ->  installmentService.addInstallment(installment));

        return timetable.getId();
    }
    @GetMapping("/credit/timetable")
    public List<Installment> getTimetable(@RequestParam int id){
        return  timetableService.findTimetableById(id).getInstalments();
    }
}
