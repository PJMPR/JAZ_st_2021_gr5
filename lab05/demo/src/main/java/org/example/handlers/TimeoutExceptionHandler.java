package org.example.handlers;
import org.example.ExceptionProvider;
import org.example.logger.FileLogger;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TimeoutExceptionHandler implements ExceptionHandler {
    boolean reload = true;
    FileLogger fileLogger = new FileLogger();

    @Override
    public void handle(ExceptionProvider method, Exception exception, String fileMessage) throws IOException {
        if (canHandle(exception)) {
            while (reload) {
                try {
                    System.out.println("Connection timeout. Attempting to reconnect...");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    fileLogger.LogMessage(fileMessage);
                    reload = false;
                }
            }
        }
    }

    @Override
    public boolean canHandle(Exception exception) {
        return exception instanceof TimeoutException;
    }
}
