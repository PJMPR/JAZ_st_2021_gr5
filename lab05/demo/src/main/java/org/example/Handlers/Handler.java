package org.example.Handlers;

import org.example.Provider;

public interface Handler {
    String info();
    boolean canHandle(Exception e);
    boolean handle(Exception e, Provider method);
    void log();
}