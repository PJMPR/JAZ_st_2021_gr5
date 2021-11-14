package org.example.tests;

import org.example.exeptions.FileNotFoundThrow;
import org.example.handlers.FileNotFoundExceptionHandler;
import org.junit.Assert;
import org.junit.Test;


public class FileNotFoundHandleTest {
    Throwable throwable;

    @Test
    public void exceptionInvokerShouldInvokeExceptionAndHandlerShouldCatchIt() {
        FileNotFoundThrow fileNotFoundThrow = new FileNotFoundThrow();
        fileNotFoundThrow.exceptionInvoker();
    }

    @Test
    public void fileNotFoundExceptionHandlerShouldHandleException() {
        FileNotFoundExceptionHandler exceptionHandler = new FileNotFoundExceptionHandler();
        Assert.assertTrue(exceptionHandler.handleException(throwable));
    }
}
