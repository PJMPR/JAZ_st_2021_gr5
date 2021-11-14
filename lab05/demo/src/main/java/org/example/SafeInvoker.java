package org.example;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.example.Errors.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeInvoker {
    List<ErrorHandler> errorHandlers = new ArrayList<>(List.of(
            new FileNotFoundHandler(),
            new TimeoutHandler(),
            new ClassNotFoundHandler(),
            new SQLExceptionHandler()
    ));

    public void invoke(ErrorSupplier method){
        String log4jConfPath = "src/main/Logs/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        DefaultErrorHandler defaultErrorHandler = new DefaultErrorHandler();
        try {
            method.execute();
        } catch (Exception e){
            AtomicBoolean wasHandled = new AtomicBoolean(false);
            errorHandlers.stream()
                    .filter(errorHandler -> errorHandler.canHandle(e))
                    .forEach(errorHandler -> {
                        errorHandler.handle(e,method);
                        wasHandled.set(true);
                    });
            if (!wasHandled.get()) defaultErrorHandler.handle(e,method);
        }

    }
}
