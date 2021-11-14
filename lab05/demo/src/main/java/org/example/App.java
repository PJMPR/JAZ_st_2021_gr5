package org.example;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException {
        Runnable task1 = () -> System.out.println("Task nr. 1 0successfully completed");

        Runnable task2 = () -> {
            throw new RetryException();
        };

        Runnable task3 = () -> {
            throw new UnsupportedOperationException();
        };

        SafeInvoker safeInvoker = new SafeInvoker();

        safeInvoker.invoke(task1);
        safeInvoker.invoke(task2);
        safeInvoker.invoke(task3);
    }
}
