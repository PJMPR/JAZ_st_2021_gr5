package org.example.handlers;

import org.apache.log4j.Logger;
import org.example.Provider;

public class ClassNotFoundExceptionH implements ExceptionHandler {
    private final Logger logger = Logger.getLogger(ClassNotFoundExceptionH.class.getName());

    @Override
    public String getMessage() {
        return "Class was not found";
    }

    @Override
    public boolean canHandle(Exception exception) {
        return exception instanceof ClassNotFoundException;
    }

    @Override
    public boolean handle(Exception exception, Provider method) {
        if (canHandle(exception)) {
            System.out.println(getMessage());
            log();
            return true;
        }
        return false;
    }

    @Override
    public void log() {
        logger.error(getMessage());
    }
}
