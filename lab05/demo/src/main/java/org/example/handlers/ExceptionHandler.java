package org.example.handlers;

import org.example.Provider;

public interface ExceptionHandler {
    String getMessage();

    boolean canHandle(Exception exception);

    boolean handle(Exception exception, Provider method);

    void log();
}
