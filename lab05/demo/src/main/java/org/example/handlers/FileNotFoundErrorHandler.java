package org.example.handlers;

import org.example.Supplier;
import java.io.FileNotFoundException;

public class FileNotFoundErrorHandler implements ErrorHandler {

    @Override
    public String getMessage() {
        return "File was not found. Please check your file path.";
    }

    @Override
    public void handle(Exception e, Supplier m) {
        if(canHandle(e)){
            System.out.println(getMessage());
        }
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof FileNotFoundException;
    }
}