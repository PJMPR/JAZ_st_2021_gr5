package org.example.Handlers;

import org.apache.log4j.Logger;
import org.example.Fixes.Fixer;
import org.example.Provider;
import java.util.concurrent.TimeoutException;

public class TimeOutHandler implements Handler {
    private final Logger logger = Logger.getLogger(TimeOutHandler.class.getName());
    @Override
    public String info() {
        return "Connection timed out, check internet connection";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof TimeoutException;
    }

    @Override
    public boolean handle(Exception e, Provider method) {
        if (canHandle(e)) {
            System.out.println("Connection timed out, reconnecting");
            Fixer.wait(2);
            if (Fixer.reapeat(method,3)) {
                return true;
            }
            System.out.println(info());
            log();
        }
        return false;
    }

    @Override
    public void log() {
        logger.error(info());
    }
}