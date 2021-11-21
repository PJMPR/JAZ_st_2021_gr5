package com.example.demo.fileWriter;

import com.example.demo.service.TimetableService;
import com.example.demo.timetable.Installment;
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
public class PdfFileWriter implements FileWriter{
    @Override
    public void getFile(HttpServletResponse response, int id, TimetableService timetableService) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Timetable id="+id, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2.0f, 3.0f, 3.0f, 3.0f, 3.0f,3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);

        List<Installment> installmentList= timetableService.findTimetableById(id).getInstalments();
        for (Installment installment : installmentList) {
            table.addCell(String.valueOf(installment.getId()));
            table.addCell(String.valueOf(installment.getCapital()));
            table.addCell(String.valueOf(installment.getCapitalToPay()));
            table.addCell(String.valueOf(installment.getFixedFee()));
            table.addCell(String.valueOf(installment.getInterest()));
            table.addCell(String.valueOf(installment.getAmount()));
        }

        document.add(table);

        document.close();
    }
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Number", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("percentage of capital", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("capital to pay", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("fixed fee", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("interest", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("amount", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {

    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {


    }
}
