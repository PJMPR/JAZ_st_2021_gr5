package org.example.Handlers;

import org.example.Supplier;

public class ClassNotFoundExceptionHandler implements ErrorHandler {
    @Override
    public String getMessage() {
        return "could not find that class";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        if (canHandle(err)){
            System.out.println(getMessage());
        }
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof ClassNotFoundException;
    }
}
