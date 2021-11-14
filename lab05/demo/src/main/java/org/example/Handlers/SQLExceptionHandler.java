package org.example.Handlers;

import org.apache.log4j.Level;
import org.example.Supplier.Supplier;
import org.example.actions.Actions;

import java.sql.SQLException;

public class SQLExceptionHandler implements ErrorHandler {


    @Override
    public String getMessage() {
        return "Could not connect to database. Check your internet connection";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        if (canHandle(err)) {

            System.out.println("Could not connect to database. Reconnecting...");
            actions.wait(2);

            if (actions.redo(method, 5)) {
                return;
            }
            System.out.println(getMessage());
            logger.log(getMessage());
        }
    }


    @Override
    public boolean canHandle(Exception err) {
        return err instanceof SQLException;
    }
}
