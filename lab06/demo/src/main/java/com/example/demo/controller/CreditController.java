package com.example.demo.controller;

import com.example.demo.creditParameters.CreditParameters;
import com.example.demo.fileWriter.CsvFileWriter;
import com.example.demo.fileWriter.PdfFileWriter;
import com.example.demo.service.InstallmentService;
import com.example.demo.service.TimetableService;
import com.example.demo.timetable.Installment;
import com.example.demo.timetable.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class CreditController {
    TimetableService timetableService;
    InstallmentService installmentService;
    CsvFileWriter csvFileWriter;
    PdfFileWriter pdfFileWriter;

    @Autowired
    public CreditController(TimetableService timetableService, InstallmentService installmentService, CsvFileWriter csvFileWriter, PdfFileWriter pdfFileWriter) {
        this.timetableService = timetableService;
        this.installmentService = installmentService;
        this.csvFileWriter = csvFileWriter;
        this.pdfFileWriter = pdfFileWriter;
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

    @GetMapping("/credit/timetable2")
    public void getFile(HttpServletResponse response,@RequestParam int id,@RequestParam String file)throws IOException{

        switch (file){
            case "csv"-> csvFileWriter.getFile(response,id,timetableService);
            case "pdf"-> pdfFileWriter.getFile(response,id,timetableService);
            default -> System.out.println("No such file type!");
        }
    }


}
