package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.result.SelftestResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SelfTestRunner {
    List<Selftest.SelfTest> selfTestList;

    public List<SelftestResult> run(){
        List<SelftestResult> result = new ArrayList<>();
        selfTestList.forEach(selfTest -> result.add(selfTest.execute()));
        return result;
    }
}