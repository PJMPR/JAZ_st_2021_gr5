package org.example;

import org.example.Handlers.*;

import java.util.ArrayList;
import java.util.List;


public class SafeInvoker {
    List<ErrorHandler> errorHandlersList = new ArrayList<>(List.of(
            new FileNotFoundErrorHandler(),
            new TimeoutHandler(),
            new SQLExceptionHandler(),
            new ClassNotFoundExceptionHandler()
    ));

    public void invoke(Supplier method){
        try {
            method.execute();
        } catch (Exception err){
               errorHandlersList.forEach(error1 -> error1.handle(err, method));
        }
    }
}
//Supplier<Exception> exceptionSupplier