package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.outcome.SelftestOutcome;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SelfTestBase implements Selftest.SelfTest {
    public String name;
    public String description;

    abstract public SelftestOutcome execute();
}