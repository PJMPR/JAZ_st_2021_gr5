package com.pjwstk.sakila.diagnostics.selftest;

import org.springframework.stereotype.Component;

@Component
public class CheckDiskSpaceSelfTest extends SelfTestBase {

    public CheckDiskSpaceSelfTest(String name, String description){
        super.name=name;
        super.description=description;
    }

    public SelfTestResult run(){
        return new SelfTestResult("","",true,null);
    }
}
