package org.example.handlers;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class FileNotFoundExceptionHandler implements HandleException {
    private final Logger logger = Logger.getLogger(FileNotFoundExceptionHandler.class);

    @Override
    public String getHandleExceptionSimpleName() {
        return "FileNotFoundException";
    }

    @Override
    public boolean handleException(Throwable e) {
        System.out.println("Nie znaleziono pliku, stworz go");
        logMessage(e);
        return true;
    }

    private void logMessage(Throwable e) {
        logger.log(Level.ERROR, "Exception has been handled");
    }
}
