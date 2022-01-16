package com.pjwstk.sakila.diagnostics.selftest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class SelfTestResult {
    String name;
    String description;
    boolean passed;
    List<String> errors;
}

