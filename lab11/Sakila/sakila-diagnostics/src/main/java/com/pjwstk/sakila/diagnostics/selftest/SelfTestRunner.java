package com.pjwstk.sakila.diagnostics.selftest;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelfTestRunner {
    List<iSelfTest> selfTests = List.of(new CheckDiskSpaceSelfTest());

    public List<SelfTestResult> run(){
        List<SelfTestResult> results = selfTests.stream().map(iSelfTest::run).collect(Collectors.toList());
        return new ArrayList<>();
    }
}
