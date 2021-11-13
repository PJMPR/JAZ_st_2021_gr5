package org.example;

import org.example.ErrorHandlers.ErrorHandler;
import org.example.ErrorHandlers.ErrorInformation;

public class SafeInvoker {
////      case "class java.io.FileNotFoundException" -> System.out.println("FileNotFoundException !");
    public void invoke(UnsafeMethod unsafeMethod) {
        try {
            unsafeMethod.execute();
        } catch (Exception e) {
            ErrorHandler errorHandler = new ErrorHandler(
                    new ErrorInformation(e.getClass().toString(), e.getMessage()));
            errorHandler.handle();
        }
    }
}