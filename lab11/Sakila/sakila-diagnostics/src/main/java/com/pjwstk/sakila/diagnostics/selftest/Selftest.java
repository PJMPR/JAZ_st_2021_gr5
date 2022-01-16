package com.pjwstk.sakila.diagnostics.selftest;
import com.pjwstk.sakila.diagnostics.outcome.SelftestOutcome;

public interface Selftest {
    public interface SelfTest {
        public SelftestOutcome execute();
    }
}