package org.example.handlers;

import org.example.ExceptionProvider;

import java.io.IOException;

public interface ExceptionHandler {
    void handle(ExceptionProvider method, Exception exception, String fileMessage) throws IOException;
    boolean canHandle(Exception exception);
}
