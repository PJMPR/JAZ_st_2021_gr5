package com.example.LoanScheduler.exporters;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.LoanScheduler.Loan.Installment;
import com.example.LoanScheduler.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

@Component
public class CSVexporter{

    public void getFile(HttpServletResponse response, int id, TimetableService timetableService) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Installment> installmentList = timetableService.getTimetable(id).getInstalments();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Number", "Capital", "capital to pay", "fixed fee", "interest", "amount"};
        String[] nameMapping = {"number", "capital", "capitalToPay", "fixedFee", "interest", "amount"};

        csvWriter.writeHeader(csvHeader);

        for (Installment installment : installmentList) {
            csvWriter.write(installment, nameMapping);
        }

        csvWriter.close();
    }
}

