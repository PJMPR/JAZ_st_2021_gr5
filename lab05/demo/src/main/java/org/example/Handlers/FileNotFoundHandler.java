package org.example.Handlers;

import org.apache.log4j.Logger;
import org.example.Provider;
import java.io.FileNotFoundException;

public class FileNotFoundHandler implements Handler {
    private final Logger logger = Logger.getLogger(FileNotFoundHandler.class.getName());

    @Override
    public String info() {
        return "File not found, check file path";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof FileNotFoundException;
    }

    @Override
    public boolean handle(Exception e, Provider method) {
        if(canHandle(e)){
            System.out.println(info());
            log();
            return true;
        }
        return false;
    }

    @Override
    public void log() {
        logger.error(info());
    }
}