package org.example.Errors;

import org.apache.log4j.Logger;
import org.example.Behavior;
import org.example.ErrorSupplier;
import java.sql.SQLException;

public class SQLExceptionHandler implements ErrorHandler{
    private final Logger logger = Logger.getLogger(SQLExceptionHandler.class.getName());
    @Override
    public String getMessage() {
        return "Could not connect to database. Check your internet connection";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof SQLException;
    }

    @Override
    public boolean handle(Exception e, ErrorSupplier method) {
        if (canHandle(e)) {
            System.out.println("Trying to reconnect to database...");
            Behavior.wait(2);
            if (Behavior.repeatOperation(method, 3)) {
                return true;
            }
            System.out.println(getMessage());
            log();
        }
        return false;
    }

    @Override
    public void log() {
        logger.error(getMessage());
    }
}
