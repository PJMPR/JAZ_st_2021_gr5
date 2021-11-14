package org.example.Handlers;


import org.example.Supplier.Supplier;
import org.example.actions.Actions;
import org.example.logger.Logger;

public interface ErrorHandler {
    Actions actions = new Actions();
    Logger logger = new Logger();
    String getMessage();
    void handle(Exception err, Supplier method);
    boolean canHandle(Exception err);

}
