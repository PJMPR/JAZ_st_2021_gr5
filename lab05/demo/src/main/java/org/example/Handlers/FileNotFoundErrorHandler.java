package org.example.Handlers;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.example.App;
import org.example.Supplier.Supplier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class FileNotFoundErrorHandler implements ErrorHandler {

    @Override
    public String getMessage() {
        return "File was not found.Please check path to your directory!";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        if(canHandle(err)){
            System.out.println(getMessage());
            logger.log(getMessage());
        }
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof FileNotFoundException;
    }
}
