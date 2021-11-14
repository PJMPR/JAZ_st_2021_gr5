package org.example;

import org.example.handlers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class SafeInvoker {
    List<ErrorHandler> errorHandlersList = new ArrayList<>(List.of(
            new FileNotFoundErrorHandler(),
            new TimeoutHandler(),
            new SQLExceptionHandler(),
            new ClassNotFoundExceptionHandler()
    ));

    public void invoke(Supplier m){
        try {
            m.execute();
        } catch (Exception e){
            AtomicBoolean wasHandled = new AtomicBoolean(false);
            errorHandlersList.stream()
                    .filter(errorHandler -> errorHandler.canHandle(e))
                    .forEach(errorHandler -> {
                        errorHandler.handle(e,m);
                        wasHandled.set(true);
                    });
            if (!wasHandled.get()){
                DefaultErrorHandler defaultErrorHandler = new DefaultErrorHandler();
                defaultErrorHandler.handle(e, m);
            }
        }
    }
}