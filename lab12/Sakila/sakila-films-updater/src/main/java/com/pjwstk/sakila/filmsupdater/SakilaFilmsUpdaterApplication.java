package com.pjwstk.sakila.filmsupdater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pjwstk.sakila")
public class SakilaFilmsUpdaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SakilaFilmsUpdaterApplication.class, args);
    }

}
