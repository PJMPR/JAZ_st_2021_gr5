package org.example.Handlers;

import org.apache.log4j.Level;
import org.example.Supplier.Supplier;

public class DefaultErrorHandler implements ErrorHandler{
    private boolean canHandle=true;
    @Override
    public String getMessage() {
        return "Something went wrong :(";
    }

    @Override
    public boolean handle(Exception err, Supplier method) {
        System.out.println(getMessage());
        log();
        return true;
    }

    @Override
    public boolean canHandle(Exception err) {
        return canHandle;
    }

    @Override
    public void log() {
        //LOGGER.log(Level.toLevel("error"),getMessage());
    }

    public boolean isCanHandle() {
        return canHandle;
    }

    public void setCanHandle(boolean canHandle) {
        this.canHandle = canHandle;
    }
}
