package com.pjwstk.sakila.logic.safe;

import com.pjwstk.sakila.logic.safe.repeaters.IRepeater;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SafeInvoker implements SafeInvoking{
    private final IRepeater repeater;


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
                try{
                    action.execute();
                    isSuccess=true;
                    exception=null;
                    break;
                }catch(Exception ex2){
                    exception=ex2;
                    repeater.For(ex2).waiting().retry();
                }
            }

        }
        return new InvokerResult(exception, isSuccess);
    }
}
