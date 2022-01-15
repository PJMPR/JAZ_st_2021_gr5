package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.selftest.tests.CheckDiskSpaceSelfTest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class SelfTestRunner {
    private List<iSelfTest> selfTests = List.of(new CheckDiskSpaceSelfTest());

    public List<SelfTestResult> run(){
        return selfTests.stream().map(iSelfTest::run).collect(Collectors.toList());
    }

    public void addTests(iSelfTest selfTest){
        selfTests.add(selfTest);
    }
}
