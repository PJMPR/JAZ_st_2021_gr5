package com.example.servicedemo;

import com.example.servicedemo.Timetable.Installment;
import com.example.servicedemo.Timetable.Timetable;
import com.example.servicedemo.fileWriters.CSVwriter;
import com.example.servicedemo.fileWriters.PDFwriter;
import com.example.servicedemo.services.InstallmentService;
import com.example.servicedemo.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    InstallmentService installmentService;
    TimetableService timetableService;
    PDFwriter PDFwriter;
    CSVwriter CSVwriter;

    @Autowired
    public Controller(InstallmentService installmentService, TimetableService timetableService, PDFwriter PDFwriter, CSVwriter CSVwriter){
        this.installmentService = installmentService;
        this.timetableService = timetableService;
        this.PDFwriter = PDFwriter;
        this.CSVwriter = CSVwriter;
    }

    @GetMapping("credit/timetable")
    public ResponseEntity<Timetable> getTimetable(@RequestParam Integer id){
        return ResponseEntity.ok(timetableService.getTimetable(id));
    }

    @PostMapping("/credit/calculations")
    public ResponseEntity<Integer> saveInstallmentData(@RequestBody Timetable timetable){
        int id =  timetableService.insertData(timetable);
        List<Installment> installments = new ArrayList<>(installmentService.calculate(timetable));
        installments.forEach(installment -> installmentService.saveInstallments(installment));
        return ResponseEntity.ok(id);
    }

    @GetMapping(value = "/credit/timetable",params = {"id","file"})
    public ResponseEntity getTimetableInFile(HttpServletResponse response, @RequestParam Integer id, @RequestParam String file) throws IOException {
        switch (file) {
            case "pdf" -> PDFwriter.getFile(response,id,timetableService);
            case "csv" -> CSVwriter.getFile(response,id,timetableService);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
