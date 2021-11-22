package com.example.demo.fileWriter;

import com.example.demo.service.TimetableService;
import com.example.demo.timetable.Installment;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CsvFileWriter implements FileWriter {
    @Override
    public void getFile(HttpServletResponse response, int id, TimetableService timetableService) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Installment> installmentList = timetableService.findTimetableById(id).getInstalments();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Number", "percentage of capital", "capital to pay", "fixed fee", "interest", "amount"};
        String[] nameMapping = {"number", "capital", "capitalToPay", "fixedFee", "interest", "amount"};

        csvWriter.writeHeader(csvHeader);

        for (Installment installment : installmentList) {
            csvWriter.write(installment, nameMapping);
        }

        csvWriter.close();
    }


}
