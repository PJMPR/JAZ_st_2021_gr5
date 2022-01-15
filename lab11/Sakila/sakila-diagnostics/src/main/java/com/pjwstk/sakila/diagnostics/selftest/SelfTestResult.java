package com.pjwstk.sakila.diagnostics.selftest;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SelfTestResult {
    String name;
    String description;
    boolean passed;
    List<String> errors;
}
