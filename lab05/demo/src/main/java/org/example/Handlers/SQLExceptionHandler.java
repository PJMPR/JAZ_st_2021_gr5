package org.example.Handlers;

import org.example.Supplier;

import java.sql.SQLException;

public class SQLExceptionHandler implements ErrorHandler{

    @Override
    public String getMessage() {
        return "Could not connect to database. Check your internet connection";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        if(canHandle(err)){
            try {
                System.out.println("Could not connect to database. Reconnecting...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(redo(method)){
                return;
            }
            System.out.println(getMessage());
        }
    }

    public boolean redo(Supplier method){
        try {
            method.execute();
            return true;
        } catch (Exception err){
            return false;
        }
    }

    @Override
    public boolean canHandle(Exception err) {
        return err instanceof SQLException;
    }
}
