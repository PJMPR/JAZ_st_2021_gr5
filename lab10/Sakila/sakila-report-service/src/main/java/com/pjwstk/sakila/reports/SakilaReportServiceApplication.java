package com.pjwstk.sakila.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pjwstk.sakila")
public class SakilaReportServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SakilaReportServiceApplication.class, args);
    }

}
