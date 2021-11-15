package org.example.errors;

import org.example.Supplier;

import org.apache.log4j.Logger;

public class DefaultException implements ExceptionHandler {
    private final Logger logger = Logger.getLogger(DefaultException.class.getName());
    private boolean canHandle = true;


    @Override
    public String getMessage() {
        return "Unknown exception";
    }

    @Override
    public boolean canHandle(Exception exception) {
        return canHandle;
    }

    @Override
    public boolean handle(Exception exception, Supplier method) {
        System.out.println(getMessage());
        log();
        return true;
    }

    @Override
    public void log() {
        logger.error(getMessage());
    }
}
