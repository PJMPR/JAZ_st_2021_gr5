package com.pjwstk.sakila.diagnostics.selftest;
import com.pjwstk.sakila.diagnostics.result.SelftestResult;

public abstract class SelfTestBase implements Selftest.SelfTest {
    String name;
    String description;

    abstract public SelftestResult execute();
}
