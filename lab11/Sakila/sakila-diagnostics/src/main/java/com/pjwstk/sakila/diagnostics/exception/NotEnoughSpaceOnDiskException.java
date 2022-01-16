package com.pjwstk.sakila.diagnostics.exception;

public class NotEnoughSpaceOnDiskException extends Exception {
    public NotEnoughSpaceOnDiskException(String errorMessage) {
        super(errorMessage);
    }
}
