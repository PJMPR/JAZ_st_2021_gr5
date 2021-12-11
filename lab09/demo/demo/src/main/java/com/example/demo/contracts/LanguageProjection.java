package com.example.demo.contracts;

import org.springframework.beans.factory.annotation.Value;

public interface LanguageProjection {
    @Value("#{target.languageId}")
    int getId();

    String getName();
}
