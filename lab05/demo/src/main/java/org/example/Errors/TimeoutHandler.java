package org.example.Errors;

import org.apache.log4j.Logger;
import org.example.Behavior;
import org.example.ErrorSupplier;
import java.util.concurrent.TimeoutException;

public class TimeoutHandler implements ErrorHandler{
    private final Logger logger = Logger.getLogger(TimeoutHandler.class.getName());
    @Override
    public String getMessage() {
        return "Connection timed out. Check your internet connection.";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof TimeoutException;
    }

    @Override
    public boolean handle(Exception e, ErrorSupplier method) {
        if (canHandle(e)) {
            System.out.println("Connection timed out. Trying to reconnect...");
            Behavior.wait(2);
            if (Behavior.repeatOperation(method,3)) {
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
