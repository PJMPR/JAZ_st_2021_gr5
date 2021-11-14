package org.example.handlers;
import org.example.ExceptionProvider;
import org.example.logger.FileLogger;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileNotFoundExceptionHandler implements ExceptionHandler {
    FileLogger fileLogger = new FileLogger();

    @Override
    public void handle(ExceptionProvider method, Exception exception, String fileMessage) throws IOException {
        if (canHandle(exception)) {
            System.out.println("The file you are trying to open does not exist or the path to it is wrong.");
            fileLogger.LogMessage(fileMessage);
        }
    }

    @Override
    public boolean canHandle(Exception exception) {
        return exception instanceof FileNotFoundException;
    }
}
