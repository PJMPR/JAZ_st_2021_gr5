package org.example.logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger {

    @Override
    public void LogMessage(String message) throws IOException {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime logDate = LocalDateTime.now();
        String logMessage = ("Date: " + logDate + " Message: " + message + "\n");
        Files.write(Paths.get("lab05/demo/src/main/java/org/example/logger/log.txt"), logMessage.getBytes(), StandardOpenOption.APPEND);
    }
}
