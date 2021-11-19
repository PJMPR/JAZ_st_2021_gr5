package org.example;

import org.example.repeaters.IRepeater;

public class SafeInvoker implements ISafeInvoker{

    IRepeater repeater;

    public SafeInvoker(IRepeater repeater) {
        this.repeater = repeater;
    }

    @Override
    public InvokerResult SafeInvoke(NotSafeAction action) {

        var isSuccess = false;
        Exception exception = null;
        try {
            action.execute();
            isSuccess=true;
        }catch(Exception ex){

            repeater.For(ex);
            exception=ex;
            while (repeater.shouldRetry()){
                try {
                    action.execute();
                    isSuccess=true;
                    exception=null;
                }catch(Exception ex2){
                    exception=ex2;
                    repeater.For(ex2).waiting().retry();
                }
            }

        }
        return new InvokerResult(exception, isSuccess);
    }
}
