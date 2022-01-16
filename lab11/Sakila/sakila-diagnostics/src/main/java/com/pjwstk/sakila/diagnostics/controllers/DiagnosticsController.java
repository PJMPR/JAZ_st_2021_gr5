package com.pjwstk.sakila.diagnostics.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pjwstk.sakila.diagnostics.selftest.*;
import java.util.Arrays;

@Controller
@RequestMapping("diagnostics")
public class DiagnosticsController {

    @GetMapping("status")
    public ResponseEntity status(){

        return ResponseEntity.ok("ALIVE");
    }
    @GetMapping("selftest")
    public ResponseEntity selftest(){
        SelfTestRunner selfTestRunner = new SelfTestRunner();

        selfTestRunner.setSelfTestList(Arrays.asList(new DiskStorageSelfTest()));
        return ResponseEntity.ok(selfTestRunner.run());
    }
}
