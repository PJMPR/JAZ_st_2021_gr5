package org.example;

import org.example.exeptions.ExceptionThrower;
import org.example.exeptions.IOExceptionThrow;
import org.example.exeptions.FileNotFoundExceptionThrow;
import org.example.handlers.FileNotFoundExceptionHandler;
import org.example.handlers.HandleException;
import org.example.handlers.IOExceptionHandler;

import java.lang.reflect.Method;
import java.util.List;

public class SafeInvoker {
    List<ExceptionThrower> exceptionsList = List.of(
            new FileNotFoundExceptionThrow(),
            new IOExceptionThrow()
    );

    List<HandleException> handleExceptions = List.of(
            new FileNotFoundExceptionHandler(),
            new IOExceptionHandler()
    );

//    public void invoke() {
//        exceptionsList.forEach(exceptionThrower -> {
//            try {
//                exceptionThrower.exception();
//            } catch (NoSuchFieldException e) {
//                new NoSuchFileExceptionHandler().handleException(e);
//            } catch (IOException e) {
//                new IOExceptionHandler().handleException(e);
//            }
//        });
//    }

    public void invoke(Object object, String methodName, Object... args) {
        Method method = getMethode(object, methodName);
        HandleException handleException;

        if (method != null) {
            for(int i = 0; i < 5; i++){
                try {
                    method.invoke(object, args);
                } catch (Exception e) {
                    handleException = getHandleException(e.getCause().getClass().getSimpleName());
                    if( handleException != null) {
                        if(handleException.handleException(e)){
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
}