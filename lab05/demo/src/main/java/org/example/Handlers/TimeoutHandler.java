package org.example.Handlers;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.example.Supplier.Supplier;

import java.util.concurrent.TimeoutException;

public class TimeoutHandler implements ErrorHandler {

    @Override
    public String getMessage() {
        return "Connection timed out. Check your internet connection.";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        if (canHandle(err)) {
            System.out.println("Connection timed out. Trying to reconnect...");
            actions.wait(2);
            if (actions.redo(method,5)) {
                return;
            }
            System.out.println(getMessage());
            logger.log(getMessage());
        }
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof TimeoutException;
    }
}
