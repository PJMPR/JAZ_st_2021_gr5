package org.example;

import org.apache.log4j.PropertyConfigurator;
import org.example.Handlers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeInvoker {
    List<Handler> HandlersList = new ArrayList<>(List.of(
            new FileNotFoundHandler(),
            new TimeOutHandler(),
            new ClassNotFoundHandler(),
            new SQLExceptionHandler()
    ));

    public void invoke(Provider action){
        String log4jPath = "src/main/java/org/example/Loger/log4j.properties";
        PropertyConfigurator.configure(log4jPath);
        DefaultErrorHandler defaultErrorHandler = new DefaultErrorHandler();
        try {
            action.execute();
        } catch (Exception e){

            AtomicBoolean wasHandled = new AtomicBoolean(false);

            HandlersList.stream()
                    .filter(errorHandler -> errorHandler.canHandle(e))
                    .forEach(handler -> {
                        handler.handle(e,action);
                        wasHandled.set(true);
                    });
            if (!wasHandled.get()) defaultErrorHandler.handle(e,action);
        }

    }
}