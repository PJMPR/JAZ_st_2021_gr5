package com.pjwstk.sakila.diagnostics.selftest;

import org.springframework.stereotype.Component;


@Component
public class CheckDiskSpaceSelfTest implements iSelfTest {

    public SelfTestResult run(){
        return new SelfTestResult("Disk space check",
                "Checking if at least 5% free disk space is avaliable"
                ,true,null);
    }
}
