package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;

class TestExceptions {

    @Test
    void testWhenExceptionIsNotThrown() throws IOException, InterruptedException {
        Runnable task = () -> System.out.println("Task #1 success");

        boolean result = new SafeInvoker().invoke(task);
        Assertions.assertTrue(result);
    }

    @Test
    void testWhenRetryExceptionIsThrown() throws IOException, InterruptedException {
        Runnable task = () -> {
            throw new RetryException();
        };

        boolean result = new SafeInvoker().invoke(task);

        Assertions.assertFalse(result);
    }

    @Test
    void testWhenUnsupportedExceptionIsThrown() {
        Runnable task = () -> {
            throw new UnsupportedOperationException("Unsupported operation");
        };

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> new SafeInvoker().invoke(task));

        Assertions.assertEquals("Unsupported operation", exception.getMessage());
    }


}
