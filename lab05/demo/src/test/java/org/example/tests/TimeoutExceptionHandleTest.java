package org.example.tests;

import org.example.exeptions.TimeoutExceptionThrow;
import org.junit.Test;

public class TimeoutExceptionHandleTest {

    @Test
    public void exceptionInvokerShouldInvokeExceptionAndHandlerShouldCatchIt() {
        TimeoutExceptionThrow exceptionThrow = new TimeoutExceptionThrow();
        exceptionThrow.exceptionInvoker();
    }
}
