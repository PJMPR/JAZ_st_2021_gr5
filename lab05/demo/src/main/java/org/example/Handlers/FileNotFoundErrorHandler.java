package org.example.Handlers;

import org.example.Supplier;

import java.io.FileNotFoundException;

public class FileNotFoundErrorHandler implements ErrorHandler {


    @Override
    public String getMessage() {
        return "File was not found.Please check path to your directory!";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        if(canHandle(err)){
            System.out.println(getMessage());
        }
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof FileNotFoundException;
    }
}
