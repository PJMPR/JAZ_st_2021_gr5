package com.example.servicedemo.fileWriters;

import com.example.servicedemo.Timetable.Installment;
import com.example.servicedemo.services.TimetableService;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class PDFwriter {
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(7);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Capital", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Interest", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Fixed Rate", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Capital to pay", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);
    }

    public void writeTableData(PdfPTable table, TimetableService timetableService, int id) {
        List<Installment> installmentList = timetableService.getTimetable(id).getInstallments();
        for (Installment installment : installmentList) {
            table.addCell(String.valueOf(installment.getNumber()));
            table.addCell(String.valueOf(installment.getCapital()));
            table.addCell(String.valueOf(installment.getInterest()));
            table.addCell(String.valueOf(installment.getFixedFee()));
            table.addCell(String.valueOf(installment.getCapitalToPay()));
            table.addCell(String.valueOf(installment.getAmount()));
        }
    }

    public void getFile(HttpServletResponse response, int id, TimetableService timetableService) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(14);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Installments schedule", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table,timetableService,id);

        document.add(table);

        document.close();
    }
}
