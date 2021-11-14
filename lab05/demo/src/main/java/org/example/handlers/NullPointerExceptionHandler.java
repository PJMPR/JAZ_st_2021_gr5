package org.example.handlers;
import org.example.ExceptionProvider;
import org.example.logger.FileLogger;
import java.io.IOException;

public class NullPointerExceptionHandler implements ExceptionHandler {
    FileLogger fileLogger = new FileLogger();

    @Override
    public boolean canHandle(Exception exception) {
        return exception instanceof NullPointerException;
    }

    @Override
    public void handle(ExceptionProvider method, Exception exception,  String fileMessage) throws IOException {
        if(canHandle(exception)) {
            System.out.println("Trying to do something on null value. Make sure that value exists.");
            fileLogger.LogMessage(fileMessage);
        }
    }
}
