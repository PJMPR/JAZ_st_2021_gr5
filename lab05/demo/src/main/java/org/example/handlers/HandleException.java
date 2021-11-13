package org.example.handlers;

public interface HandleException {
    String getHandleExceptionSimpleName();
    boolean handleException(Throwable e);
}
