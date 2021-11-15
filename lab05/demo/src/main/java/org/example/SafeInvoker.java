package org.example;

import org.example.handlers.*;
import org.apache.log4j.PropertyConfigurator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeInvoker {
    List<ExceptionHandler> exceptionHandlers = new ArrayList<>(List.of(
            new FileNotFoundExceptionH(),
            new ClassNotFoundExceptionH(),
            new TimeoutExceptionH(),
            new SQLExceptionH()
    ));

    public void invoke(Provider method) {
        String log4jConfigurationPath = "src/main/java/org/example/logs/log4j.properties";
        PropertyConfigurator.configure(log4jConfigurationPath);
        DefaultException defaultHandler = new DefaultException();
        try {
            method.execute();
        } catch (Exception exception) {
            AtomicBoolean isHandled = new AtomicBoolean(false);
            exceptionHandlers.stream()
                        .filter(exceptionHandler -> exceptionHandler.canHandle(exception))
                        .forEach(exceptionHandler -> {
                            exceptionHandler.handle(exception, method);
                            isHandled.set(true);
                        });
            if (!isHandled.get()) {
                defaultHandler.handle(exception, method);
            }
        }
    }
}
