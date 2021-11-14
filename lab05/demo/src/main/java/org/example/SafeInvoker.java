package org.example;

import org.example.Handlers.*;
import org.example.Supplier.Supplier;
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

    DefaultErrorHandler defaultErrorHandler = new DefaultErrorHandler();

    public void invoke(Supplier method){
        try {
            method.execute();
        } catch (Exception err){
            AtomicBoolean wasHandled = new AtomicBoolean(false);
            errorHandlersList.stream()
                    .filter(errorHandler -> errorHandler.canHandle(err))
                    .forEach(errorHandler -> {
                        errorHandler.handle(err,method);
                        wasHandled.set(true);
                    });
            if (!wasHandled.get()) defaultErrorHandler.handle(err,method);
        }

    }
}
