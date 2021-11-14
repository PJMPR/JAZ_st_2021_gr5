package org.example.Handlers;

import org.apache.log4j.Level;
import org.example.Supplier.Supplier;

public class DefaultErrorHandler implements ErrorHandler{

    @Override
    public String getMessage() {
        return "Something went wrong :(";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        System.out.println(getMessage());
        logger.log(getMessage(),err);
    }

    @Override
    public boolean canHandle(Exception err) {
        return true;
    }
}
