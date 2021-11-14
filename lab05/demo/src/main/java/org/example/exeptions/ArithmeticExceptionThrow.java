package org.example.exeptions;

import org.example.SafeInvoker;


public class ArithmeticExceptionThrow {
    public void exceptionInvoker() {
        SafeInvoker safeInvoker = new SafeInvoker();
        safeInvoker.invoke(this, "divideBy0");
    }

    public int divideBy0() throws ArithmeticException{
        return 1 /0;
    }
}
