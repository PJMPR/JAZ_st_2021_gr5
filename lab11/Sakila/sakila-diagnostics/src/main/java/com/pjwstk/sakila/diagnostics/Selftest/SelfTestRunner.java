package com.pjwstk.sakila.diagnostics.Selftest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class SelfTestRunner {

    @Autowired
    List<Selftest> selfTests;

    public List<SelfTestResult> run(){
        return selfTests.stream().map(Selftest::run).collect(Collectors.toList());
    }

}
