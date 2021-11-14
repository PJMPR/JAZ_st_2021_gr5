package org.example.Errors;

import org.apache.log4j.Logger;
import org.example.ErrorSupplier;

public class ClassNotFoundHandler implements ErrorHandler {
    private final Logger logger = Logger.getLogger(ClassNotFoundHandler.class.getName());

    @Override
    public String getMessage() {
        return "Class not found.";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof ClassNotFoundException;
    }

    @Override
    public boolean handle(Exception e, ErrorSupplier method) {
        if (canHandle(e)){
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
