package org.example.Handlers;

import org.apache.log4j.Logger;
import org.example.Fixes.Fixer;
import org.example.Provider;
import java.sql.SQLException;

public class SQLExceptionHandler implements Handler {
    private final Logger logger = Logger.getLogger(SQLExceptionHandler.class.getName());
    @Override
    public String info() {
        return "Cant connect to database";
    }

    @Override
    public boolean canHandle(Exception e) {
        return e instanceof SQLException;
    }

    @Override
    public boolean handle(Exception e, Provider method) {
        if (canHandle(e)) {
            System.out.println("Trying to reconnect to database...");
            Fixer.wait(2);
            if (Fixer.reapeat(method, 3)) {
                return true;
            }
            System.out.println(info());
            log();
        }
        return false;
    }

    @Override
    public void log() {
        logger.error(info());
    }
}