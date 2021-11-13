package org.example.ErrorHandlers;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ErrorHandler {
    public Logger LOGGER=Logger.getLogger(ErrorHandler.class);
    public ErrorInformation errorInformation;

    public ErrorHandler(ErrorInformation errorInformation) {
        this.errorInformation=errorInformation;
        Logger LOGGER=Logger.getLogger(ErrorHandler.class);
    }

    public ErrorInformation getErrorInformation() {
        return errorInformation;
    }

    public void handle() {
        logErrorMessage();


    }

    private  void logErrorMessage(){
        LOGGER.log(Level.ERROR, "Error name: " + errorInformation.getErrorName()
                + ", error message: "+ errorInformation.getErrorMessage()
                + ", error type: "+ errorInformation.getErrorType());
    }
}
