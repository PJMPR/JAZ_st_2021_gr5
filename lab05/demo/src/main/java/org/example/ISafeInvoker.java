package org.example;

public interface ISafeInvoker {

    InvokerResult SafeInvoke(NotSafeAction action);

    record InvokerResult(Exception exception, boolean isSuccess){}
}
