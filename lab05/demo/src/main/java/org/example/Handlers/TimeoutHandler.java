package org.example.Handlers;

import org.example.Supplier;

import java.util.concurrent.TimeoutException;

public class TimeoutHandler implements ErrorHandler{


    @Override
    public String getMessage() {
        return "Connection timed out. Check your internet connection.";
    }

    @Override
    public void handle(Exception err, Supplier method) {
        if(canHandle(err)){
            try {
                System.out.println("Connection timed out. Trying to reconnect...");
                Thread.sleep(5000);
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
        return err instanceof TimeoutException;
    }
}
