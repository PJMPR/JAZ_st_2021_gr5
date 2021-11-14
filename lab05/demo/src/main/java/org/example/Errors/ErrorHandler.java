package org.example.Errors;

import org.example.ErrorSupplier;

public interface ErrorHandler {
    String getMessage();
    boolean canHandle(Exception e);
    boolean handle(Exception e, ErrorSupplier method);
    void log();

}
