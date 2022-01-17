package com.pjwstk.sakila.diagnostics.Selftest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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
