package org.example.Errors;

import org.apache.log4j.Logger;
import org.example.ErrorSupplier;

public class DefaultErrorHandler implements ErrorHandler{
    private final Logger logger = Logger.getLogger(DefaultErrorHandler.class.getName());
    private boolean canHandle = true;

    @Override
    public String getMessage() {
        return "Unknown error occured.";
    }

    @Override
    public boolean canHandle(Exception e) {
        return canHandle;
    }

    @Override
    public boolean handle(Exception e, ErrorSupplier method) {
        System.out.println(getMessage());
        log();
        return true;
    }

    @Override
    public void log() {
        logger.error(getMessage());
    }

}
