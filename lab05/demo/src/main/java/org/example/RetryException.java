package org.example;

public class RetryException extends RuntimeException {
    public RetryException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public RetryException() {

    }
}
