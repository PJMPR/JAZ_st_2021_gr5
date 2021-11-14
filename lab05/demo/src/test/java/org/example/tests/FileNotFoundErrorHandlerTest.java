package org.example.tests;

import org.example.Handlers.FileNotFoundErrorHandler;
import org.example.SafeInvoker;
import org.example.Supplier.Supplier;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;



public class FileNotFoundErrorHandlerTest {

    Supplier method;

    FileNotFoundErrorHandler fileHandler = new FileNotFoundErrorHandler();

    @Test
    public void CheckIfFileNotFoundExceptionIsHandled(){
        method = () -> {throw new FileNotFoundException();};
        Assertions.assertTrue(fileHandler.handle(new FileNotFoundException(),method));
    }
}
