package org.example.Handlers;

import org.apache.log4j.Logger;
import org.example.Provider;

public class ClassNotFoundHandler implements Handler {
    private final Logger logger = Logger.getLogger(ClassNotFoundHandler.class.getName());

    @Override
    public String info() {
        return "Class not found.";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof ClassNotFoundException;
    }

    @Override
    public boolean handle(Exception e, Provider method) {
        if (canHandle(e)){
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