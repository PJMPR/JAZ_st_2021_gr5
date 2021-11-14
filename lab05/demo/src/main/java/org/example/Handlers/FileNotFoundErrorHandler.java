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
    public boolean handle(Exception err, Supplier method) {
        if(canHandle(err)){
            System.out.println(getMessage());
            log();
            return true;
        }
        return false;
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof FileNotFoundException;
    }

    @Override
    public void log() {
        org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(App.class);
        try {
            BasicConfigurator.configure(new FileAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"),"logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.ERROR,getMessage());
    }
}
