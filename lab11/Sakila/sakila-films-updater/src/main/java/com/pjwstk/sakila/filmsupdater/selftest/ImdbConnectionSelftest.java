package com.pjwstk.sakila.filmsupdater.selftest;

import com.pjwstk.sakila.diagnostics.selftest.SelfTestBase;
import com.pjwstk.sakila.diagnostics.selftest.SelfTestResult;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Component
public class ImdbConnectionSelftest implements SelfTestBase {

    public SelfTestResult run() {
        String APIkey = "APIkey";
        String APIkey_url = "https://imdb-api.com/en/API/Posters/" + APIkey + "/tt1375666";

        SelfTestResult result;
        try {
            Connection connection = DriverManager.getConnection(APIkey_url);
            connection.getMetaData().getDatabaseProductName();

            result = SelfTestResult.builder()
                    .name("IMDB Connection Check")
                    .description("Checking if connection to IMDB can be established successfully")
                    .passed(true)
                    .errors(null)
                    .build();
        } catch (Exception E) {
            result = SelfTestResult.builder()
                    .name("IMDB Connection Check")
                    .description("Checking if connection to IMDB can be established successfully")
                    .passed(false)
                    .errors(List.of(
                            new Exception("Connection to IMDB failed", E).getMessage()))
                    .build();
        }
        return result;
    }
}
