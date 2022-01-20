package com.pjwstk.sakila.logic.safe;

import java.util.function.Consumer;

public interface SafeInvoking {
    InvokerResult SafeInvoke(NotSafeAction action);

    record InvokerResult(Exception exception, boolean isSuccess){
        public void onUnhandledException(Consumer<Exception> consumer){
            if(isSuccess)return;
            consumer.accept(exception);
        }
    }
}
