package org.example.handlers;

import org.apache.log4j.Logger;
import org.example.Repeater;
import org.example.Provider;

import java.util.concurrent.TimeoutException;

public class TimeoutExceptionH implements ExceptionHandler {
    private final Logger logger = Logger.getLogger(TimeoutExceptionH.class.getName());

    @Override
    public String getMessage() {
        return "Connection timed out";
    }

    @Override
    public boolean canHandle(Exception exception) {
        return exception instanceof TimeoutException;
    }

    @Override
    public boolean handle(Exception exception, Provider method) {
        if (canHandle(exception)) {
            System.out.println("Connection timed out. Retrying..");
            try {
                Thread.sleep(2 + 1000L);
            } catch (Exception exception1) {
                exception1.printStackTrace();
            }
            if (Repeater.repeater(method,3)) {
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
