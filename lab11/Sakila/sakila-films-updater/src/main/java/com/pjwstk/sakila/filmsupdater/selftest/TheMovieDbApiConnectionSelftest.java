package com.pjwstk.sakila.filmsupdater.selftest;

import com.pjwstk.sakila.diagnostics.selftest.SelfTestBase;
import com.pjwstk.sakila.diagnostics.selftest.SelfTestResult;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Component
public class TheMovieDbApiConnectionSelftest implements SelfTestBase {

    public SelfTestResult run() {
        String APIkey = "APIkey";
        String APIkey_url = "https://api.themoviedb.org/3/movie/76341?api_key="+APIkey;

        SelfTestResult result;
        try {
            Connection connection = DriverManager.getConnection(APIkey_url);
            connection.getMetaData().getDatabaseProductName();

            result = SelfTestResult.builder()
                    .name("TheMovieDB Connection Check")
                    .description("Checking if connection to TheMovieDB can be established successfully")
                    .passed(true)
                    .errors(null)
                    .build();
        } catch (Exception E) {
            result = SelfTestResult.builder()
                    .name("TheMovieDB Connection Check")
                    .description("Checking if connection to TheMovieDB can be established successfully")
                    .passed(false)
                    .errors(List.of(
                            new Exception("Connection to TheMovieDB failed", E).getMessage()))
                    .build();
        }
        return result;
    }
}
