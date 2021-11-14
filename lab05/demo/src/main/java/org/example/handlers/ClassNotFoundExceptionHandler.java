package org.example.handlers;
import org.example.ExceptionProvider;
import org.example.logger.FileLogger;
import java.io.IOException;

public class ClassNotFoundExceptionHandler implements ExceptionHandler {
    FileLogger fileLogger = new FileLogger();

    @Override
    public void handle(ExceptionProvider method, Exception exception, String fileMessage) throws IOException {
        if (canHandle(exception)) {
            System.out.println("The class you are trying to reach has not been found.");
            fileLogger.LogMessage(fileMessage);
        }
    }

    @Override
    public boolean canHandle(Exception exception) {
        return exception instanceof ClassNotFoundException;
    }
}
