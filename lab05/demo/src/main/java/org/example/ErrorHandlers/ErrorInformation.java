package org.example.ErrorHandlers;

public class ErrorInformation {
    private final String errorName;
    private final String errorMessage;
    private final ErrorType errorType;

    public ErrorInformation(String fullClassName, String errorMessage) {
        this.errorName = fullClassName.split("\\.")[2];
        this.errorMessage = errorMessage;
       ErrorIdentifier  errorIdentifier= new ErrorIdentifier();
        this.errorType= errorIdentifier.identify(this);
    }

    public String getErrorName() {
        return errorName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
