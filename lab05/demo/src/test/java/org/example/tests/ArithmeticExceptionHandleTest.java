package org.example.tests;

import org.example.exeptions.ArithmeticExceptionThrow;
import org.example.handlers.ArithmeticExceptionHandle;
import org.junit.Assert;
import org.junit.Test;

public class ArithmeticExceptionHandleTest {
    Throwable throwable;

    @Test
    public void exceptionInvokerShouldInvokeExceptionAndHandlerShouldCatchIt() {
        ArithmeticExceptionThrow exceptionThrow = new ArithmeticExceptionThrow();
        exceptionThrow.exceptionInvoker();
    }

    @Test
    public void ArithmeticExceptionHandleShouldHandleException() {
        ArithmeticExceptionHandle exceptionHandle = new ArithmeticExceptionHandle();
        Assert.assertTrue(exceptionHandle.handleException(throwable));
    }
}
