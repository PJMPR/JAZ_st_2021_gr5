package org.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.example.handlers.ArithmeticExceptionHandle;
import org.example.handlers.FileNotFoundExceptionHandler;
import org.example.handlers.HandleException;
import org.example.handlers.TimeoutExceptionHandle;

import java.lang.reflect.Method;
import java.util.List;

public class SafeInvoker {
    private final Logger logger = Logger.getLogger(FileNotFoundExceptionHandler.class);

    List<HandleException> handleExceptions = List.of(
            new FileNotFoundExceptionHandler(),
            new ArithmeticExceptionHandle(),
            new TimeoutExceptionHandle()
    );

    public void invoke(Object object, String methodName, Object... args) {
        Method method = getMethode(object, methodName);
        HandleException handleException;

        if (method != null) {
            for (int i = 0; i < 5; i++) {
                try {
                    method.invoke(object, args);
                } catch (Exception e) {
                    logMessage(e.getCause().getClass().getSimpleName());
                    handleException = getHandleException(e.getCause().getClass().getSimpleName());
                    if (handleException != null) {
                        if (handleException.handleException(e)) {
                            break;
                        }
                    } else break;
                }
            }
        }
    }

    public HandleException getHandleException(String name) {
        return handleExceptions.stream()
                .filter(h -> h.getHandleExceptionSimpleName().equals(name))
                .findAny().orElseGet(null);
    }

    public Method getMethode(Object object, String methodName) {
        Method method = null;
        try {
            method = object.getClass().getMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    private void logMessage(String e){
        logger.log(Level.ERROR, "Caught: " + e);
    }
}