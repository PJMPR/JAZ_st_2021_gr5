package org.example.Errors;

import org.apache.log4j.Logger;
import org.example.ErrorSupplier;
import java.io.FileNotFoundException;

public class FileNotFoundHandler implements ErrorHandler {
    private final Logger logger = Logger.getLogger(FileNotFoundHandler.class.getName());

    @Override
    public String getMessage() {
        return "File not found. Maybe the file path is wrong?";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof FileNotFoundException;
    }

    @Override
    public boolean handle(Exception e, ErrorSupplier method) {
        if(canHandle(e)){
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
