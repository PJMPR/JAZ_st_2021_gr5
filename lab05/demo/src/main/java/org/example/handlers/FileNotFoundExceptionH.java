package org.example.handlers;

import org.apache.log4j.Logger;
import org.example.Provider;

import java.io.FileNotFoundException;

public class FileNotFoundExceptionH implements ExceptionHandler {
    private final Logger logger = Logger.getLogger(FileNotFoundExceptionH.class.getName());

    @Override
    public String getMessage() {
        return "File not found";
    }

    @Override
    public boolean canHandle(Exception exception) {
        return exception instanceof FileNotFoundException;
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
