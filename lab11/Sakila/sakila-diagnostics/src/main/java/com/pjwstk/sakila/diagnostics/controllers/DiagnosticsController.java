package com.pjwstk.sakila.diagnostics.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("diagnostics")
public class DiagnosticsController {

    @GetMapping("status")
    public ResponseEntity status(){
        return ResponseEntity.ok("ALIVE");
    }
}
