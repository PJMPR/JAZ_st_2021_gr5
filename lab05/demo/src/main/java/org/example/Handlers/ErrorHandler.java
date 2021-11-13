package org.example.Handlers;

import org.example.Supplier;

public interface ErrorHandler {
    String getMessage();
    void handle(Exception err, Supplier method);
    boolean canHandle(Exception err);
}
