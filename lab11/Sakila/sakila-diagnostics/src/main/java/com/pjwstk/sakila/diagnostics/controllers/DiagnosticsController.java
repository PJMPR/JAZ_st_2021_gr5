package com.pjwstk.sakila.diagnostics.controllers;

import com.pjwstk.sakila.diagnostics.selftest.SelfTestResult;
import com.pjwstk.sakila.diagnostics.selftest.SelfTestRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("diagnostics")
@RequiredArgsConstructor
public class DiagnosticsController {

    private final SelfTestRunner selfTestRunner;

    @GetMapping("status")
    public ResponseEntity status(){
        return ResponseEntity.ok("ALIVE");
    }

    @GetMapping("selftest")
    public ResponseEntity<List<SelfTestResult>> selfTest(){
        return ResponseEntity.ok(selfTestRunner.run());
    }
}
