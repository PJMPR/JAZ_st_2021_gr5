package org.example.handlers;

import org.example.Supplier;

public class DefaultErrorHandler implements ErrorHandler{
    private boolean canHandle=true;
    @Override
    public String getMessage() {
        return "Something went wrong :(";
    }

    @Override
    public void handle(Exception e, Supplier m) {
        System.out.println(getMessage());
    }

    @Override
    public boolean canHandle(Exception e) {
        return canHandle;
    }
}