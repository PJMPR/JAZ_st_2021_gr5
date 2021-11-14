package org.example.handlers;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class TimeoutExceptionHandle implements HandleException {
    private final Logger logger = Logger.getLogger(FileNotFoundExceptionHandler.class);
    private static int i = 0;

    @Override
    public String getHandleExceptionSimpleName() {
        return "TimeoutException";
    }

    @Override
    public boolean handleException(Throwable e) {
        if (i < 3) {
            System.out.println("Nie mozna polaczyc sie ze serwerem, sprobuj pozniej");
            logMessageFailed(e);
            i++;
            return false;
        } else {
            System.out.println("Nie mozna polaczyc sie ze serwerem, skontaktuj sie z adminem");
            logMessagePass(e);
            return true;
        }
    }

    private void logMessageFailed(Throwable e) {
        logger.log(Level.ERROR, "Exception handled, but server not responding");
    }

    private void logMessagePass(Throwable e) {
        logger.log(Level.ERROR, "Exception aborted, server not still responding");
    }
}
