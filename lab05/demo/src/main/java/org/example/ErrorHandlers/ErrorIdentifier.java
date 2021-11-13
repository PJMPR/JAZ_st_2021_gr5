package org.example.ErrorHandlers;

public class ErrorIdentifier {
    public ErrorType identify(ErrorInformation errorInformation) {
        String name = errorInformation.getErrorName();
        switch (name) {
            case "FileNotFoundException" -> {
                return ErrorType.FILE_NOT_FOUND;
            }
            case "NullPointerException" -> {
                return ErrorType.NULL_POINTER;
            }
            default -> {
                return ErrorType.UNKNOWN_ERROR;
            }
        }
    }
}
