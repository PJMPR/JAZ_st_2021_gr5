package org.example.Handlers;

import org.apache.log4j.Level;
import org.example.Supplier.Supplier;

public class ClassNotFoundExceptionHandler implements ErrorHandler {
    @Override
    public String getMessage() {
        return "could not find that class";
    }

    @Override
    public boolean handle(Exception err, Supplier method) {
        if (canHandle(err)){
            System.out.println(getMessage());
            log();
            return true;
        }
        return false;
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof ClassNotFoundException;
    }

    @Override
    public void log() {
       // LOGGER.log(Level.toLevel("error"), getMessage());
    }
}
