package com.pjwstk.sakila.reports.selftest;

import com.pjwstk.sakila.diagnostics.selftest.SelfTestBase;
import com.pjwstk.sakila.diagnostics.selftest.SelfTestResult;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Component
public class SakilaConnectionSelftest implements SelfTestBase {

    public SelfTestResult run() {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String user = "user=root";
        String password = "password=root";
        String driver = "com.mysql.jdbc.Driver";

        SelfTestResult result;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.getMetaData().getDatabaseProductName();

            result = SelfTestResult.builder()
                    .name("Sakila Connection Check")
                    .description("Checking if connection to Sakila can be established successfully")
                    .passed(true)
                    .errors(null)
                    .build();
        } catch (Exception E) {
            result = SelfTestResult.builder()
                    .name("Sakila Connection Check")
                    .description("Checking if connection to Sakila can be established successfully")
                    .passed(false)
                    .errors(List.of(
                            new Exception("Connection to Sakila failed", E).getMessage()))
                    .build();
        }
        return result;

    }
}
