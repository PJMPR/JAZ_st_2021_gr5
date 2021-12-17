package com.pjwstk.sakila.diagnostics.controlles;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("diagnostics")
public class DiagnosticsController {

    @GetMapping("isAlive")
    public ResponseEntity alive(){
        return ResponseEntity.ok("ALIVE");
    }
}
