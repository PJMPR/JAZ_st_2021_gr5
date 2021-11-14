package org.example.handlers;

import org.example.Supplier;

import java.sql.SQLException;

public class SQLExceptionHandler implements ErrorHandler {

    @Override
    public String getMessage() {
        return "Could not reconnect. Please check your internet connection.";
    }

    @Override
    public void handle(Exception e, Supplier m) {
        if (canHandle(e)) {
            System.out.println("Connection timed out. Trying to reconnect to the database...");
            SYSTEM_COMMANDS.sleep(5);
            if (!SYSTEM_COMMANDS.rerun(m,5)) {
                System.out.println(getMessage());
            }
        }
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof SQLException;
    }
}