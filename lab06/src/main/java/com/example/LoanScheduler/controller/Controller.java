package com.example.LoanScheduler.controller;

import com.example.LoanScheduler.Loan.Installment;
import com.example.LoanScheduler.Loan.Timetable;
import com.example.LoanScheduler.exporters.CSVexporter;
import com.example.LoanScheduler.exporters.PDFexporter;
import com.example.LoanScheduler.service.InstallmentService;
import com.example.LoanScheduler.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    InstallmentService installmentService;
    TimetableService timetableService;
    PDFexporter PDFexporter;
    CSVexporter CSVexporter;

    @Autowired
    public Controller(InstallmentService installmentService, TimetableService timetableService, PDFexporter PDFexporter, CSVexporter CSVexporter) {
        this.installmentService = installmentService;
        this.timetableService = timetableService;
        this.PDFexporter = PDFexporter;
        this.CSVexporter = CSVexporter;
    }

    @PostMapping("/credit/calculations")
    public long saveInstallmentData(@RequestBody Timetable timetable) {
        int id = timetableService.insertData(timetable);
        List<Installment> installments = new ArrayList<>(installmentService.calculateInstallments(timetable));
        installments.forEach(installment -> installmentService.saveInstallments(installment));
        return id;
    }

    @GetMapping("credit/timetable")
    public Timetable getTimetable(@RequestParam Integer id) {
        return timetableService.getTimetable(id);
    }

    @GetMapping(value = "/credit/timetable", params = {"id", "file"})
    public void getTimetableInFile(HttpServletResponse response, @RequestParam Integer id, @RequestParam String file) throws IOException {
        switch (file) {
            case "pdf" -> PDFexporter.getFile(response, id, timetableService);
            case "csv" -> CSVexporter.getFile(response, id, timetableService);
        }
    }
}
