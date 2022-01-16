package com.pjwstk.sakila.diagnostics.selftest;

import com.pjwstk.sakila.diagnostics.result.SelftestResult;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SakilaDbConnectionSelfTest extends SelfTestBase {

    public SakilaDbConnectionSelfTest() {
        name = "SakilaDBConnectionSelfTest";
        description = "sekila check db connection";
    }

    @Override
    public SelftestResult execute() {
        List<String> errors = new ArrayList<>();
        SelftestResult selftestResult = new SelftestResult(name, description, false, errors);
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
