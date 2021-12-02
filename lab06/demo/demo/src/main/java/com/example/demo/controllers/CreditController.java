package com.example.demo.controllers;

import com.example.demo.contract.CreditDto;
import com.example.demo.services.ICreditDbService;
import com.example.demo.services.IPdfCreator;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("credit")
public class CreditController {

    private IPdfCreator pdf;
    private ICreditDbService creditService;

    public CreditController(IPdfCreator pdf, ICreditDbService creditService) {
        this.pdf = pdf;
        this.creditService = creditService;
    }

    @PostMapping
    @RequestMapping("calculations")
    public ResponseEntity calculate(@RequestBody CreditDto credit){
        int id =creditService.saveTimetable(credit);

        UriComponents uri = ServletUriComponentsBuilder.fromCurrentContextPath().pathSegment("credit", "timetable", ""+id).build();
        return ResponseEntity.created(uri.toUri()).build();
    }

    @GetMapping
    @RequestMapping("timetable/{id}")
    public ResponseEntity timetable(@PathVariable("id") int id, @RequestParam(value = "file", required = false) String file){
        if(file==null||file.equals(""))
            return ResponseEntity.ok(creditService.getCredit(id));
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(pdf.createPdfStream().toByteArray()));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping
    @RequestMapping("pdf")
    public ResponseEntity<Resource> getPdf(){

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(pdf.createPdfStream().toByteArray()));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
