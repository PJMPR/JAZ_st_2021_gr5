package org.example.Handlers;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import org.example.Supplier.Supplier;
import org.example.actions.Actions;

public interface ErrorHandler {
    Actions actions = new Actions();
    String getMessage();
    boolean handle(Exception err, Supplier method);
    boolean canHandle(Exception err);
    void log();
}
