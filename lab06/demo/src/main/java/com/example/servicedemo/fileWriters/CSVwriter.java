package com.example.servicedemo.fileWriters;

import com.example.servicedemo.Timetable.Installment;
import com.example.servicedemo.services.TimetableService;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CSVwriter {

    public void getFile(HttpServletResponse response, int id, TimetableService timetableService) throws IOException {
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Installments_id="+ id + ".csv";
        response.setHeader(headerKey, headerValue);
        List<Installment> installmentList = timetableService.getTimetable(id).getInstallments();
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Number", "Capital", "Capital to pay", "Fixed Fee", "Interest", "Amount"};
        String[] nameMapping = {"number", "capital", "capitalToPay", "fixedFee", "interest", "amount"};
        csvWriter.writeHeader(csvHeader);
        for (Installment installment : installmentList) {
            csvWriter.write(installment, nameMapping);
        }

        csvWriter.close();
    }
}
