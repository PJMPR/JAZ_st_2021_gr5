package org.example.handlers;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ArithmeticExceptionHandle implements HandleException{
    private final Logger logger = Logger.getLogger(FileNotFoundExceptionHandler.class);

    @Override
    public String getHandleExceptionSimpleName() {
        return "ArithmeticException";
    }

    @Override
    public boolean handleException(Throwable e) {
        System.out.println("Nie mozna dzielic przez 0");
        logMessage(e);
        return true;
    }

    private void logMessage(Throwable e) {
        logger.log(Level.ERROR, "Exception has been handled");
    }
}
