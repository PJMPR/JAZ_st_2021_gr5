package com.pjwstk.sakila.diagnostics.selftest;
import com.pjwstk.sakila.diagnostics.result.SelftestResult;

public interface Selftest {
    public interface SelfTest {
        public SelftestResult execute();
    }
}
