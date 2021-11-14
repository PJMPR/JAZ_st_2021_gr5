package org.example;

@FunctionalInterface
public interface ExceptionProvider {
    void execute() throws Throwable;
}
