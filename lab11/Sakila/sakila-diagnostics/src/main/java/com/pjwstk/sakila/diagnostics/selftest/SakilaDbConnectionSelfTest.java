package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.outcome.SelftestOutcome;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component

public class SakilaDbConnectionSelfTest extends SelfTestBase {

    public SakilaDbConnectionSelfTest() {
        name = "SakilaDBConnectionSelfTest";
        description = "sekila check db connection";
    }

    @Override
    public SelftestOutcome execute() {
        List<String> errors = new ArrayList<>();
        SelftestOutcome selftestResult = new SelftestOutcome(name, description, false, errors);
        String jdbcUrl = "jdbc:mysql://localhost:3306/sakila";
        String username = "pjwstk";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);) {
            selftestResult.setPassed(true);
        }
        catch (SQLException e) {
            selftestResult.setPassed(false);
            errors.add("An sql exception, the stack: " + e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return selftestResult;
    }
}