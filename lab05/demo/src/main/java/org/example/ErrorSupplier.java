package org.example;
@FunctionalInterface
public interface ErrorSupplier {
    void execute() throws Exception;
}
