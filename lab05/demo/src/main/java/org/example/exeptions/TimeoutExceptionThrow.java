package org.example.exeptions;

import org.example.SafeInvoker;

import java.util.concurrent.TimeoutException;


public class TimeoutExceptionThrow {
    public void exceptionInvoker() {
        SafeInvoker safeInvoker = new SafeInvoker();
        safeInvoker.invoke(this, "timeoutThrower");
    }

    public void timeoutThrower() throws TimeoutException {
        throw new TimeoutException();
    }
}
