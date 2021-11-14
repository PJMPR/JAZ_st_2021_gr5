package org.example.handlers;

import org.example.Supplier;

import org.example.SystemCommands;

public interface ErrorHandler {
    SystemCommands SYSTEM_COMMANDS = new SystemCommands();
    String getMessage();
    void handle(Exception err, Supplier method);
    boolean canHandle(Exception err);
}