package org.example;


import org.example.repeaters.Repeater;
import org.example.repeaters.RepeaterExceptionRegistry;

import java.util.concurrent.TimeoutException;

class Cache{

    private static Cache instance;

    static {
        instance =  new Cache();
    }

    private Cache(){}

    public static Cache getInstance(){

        if(instance == null)
            synchronized (Cache.class){
                if(instance==null)
                    instance=new Cache();
            }
        return instance;
    }
}


public class App {

    public static void main(String[] args){

        RepeaterExceptionRegistry.getInstance().add(new ClassNotFoundException(), 0,0);
        RepeaterExceptionRegistry.getInstance().add(new TimeoutException(), 10,10);

        var result = new SafeInvoker(new Repeater(RepeaterExceptionRegistry.getInstance())).SafeInvoke(()->{});

        if(!result.isSuccess())
            result.exception().printStackTrace();

    }
}
