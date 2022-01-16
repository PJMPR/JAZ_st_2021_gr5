package com.pjwstk.sakila.diagnostics.selftest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Setter
@Getter
public class SelfTestRunner {

    private List<SelfTestBase> selfTests = List.of(new CheckDiskSpaceSelfTest());

    public List<SelfTestResult> run(){
        return selfTests.stream().map(SelfTestBase::run).collect(Collectors.toList());
    }

    public void addTests(SelfTestBase selfTest) {
        selfTests.add(selfTest);
    }
}

