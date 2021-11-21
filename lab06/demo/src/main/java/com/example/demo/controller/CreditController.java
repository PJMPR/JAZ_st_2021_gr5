package com.example.demo.controller;

import com.example.demo.creditParameters.CreditParameters;
import com.example.demo.service.InstallmentService;
import com.example.demo.service.TimetableService;
import com.example.demo.timetable.Installment;
import com.example.demo.timetable.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/credit/timetable2")
    public void exportToCSV(HttpServletResponse response,@RequestParam int id,@RequestParam String file)throws IOException{

        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Installment> installmentList = timetableService.findTimetableById(id).getInstalments();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Number", "percentage of capital", "capital to pay", "fixed fee", "interest","amount"};
        String[] nameMapping = {"number", "capital", "capitalToPay", "fixedFee", "interest","amount"};

        csvWriter.writeHeader(csvHeader);

        for (Installment installment : installmentList) {
            csvWriter.write(installment, nameMapping);
        }

        csvWriter.close();

    }
}
