package org.example.handlers;

import org.example.Supplier;

public class ClassNotFoundExceptionHandler implements ErrorHandler {
    @Override
    public String getMessage() {
        return "Class could not be found.";
    }

    @Override
    public void handle(Exception e, Supplier m) {
        if (canHandle(e)){
            System.out.println(getMessage());
        }
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof ClassNotFoundException;
    }
}