package com.pjwstk.sakila.logic.safe.repeaters;

import java.util.HashMap;
import java.util.Map;

public class RepeaterExceptionRegistry  implements IRepeaterExceptionRegistry{
    private final static RepeaterExceptionRegistry instance;
    static{
        instance = new RepeaterExceptionRegistry();
    }

    public static RepeaterExceptionRegistry getInstance(){ return instance; }

    private final Map<String, RegistryEntry> registry = new HashMap<>();

    private RepeaterExceptionRegistry(){}

    @Override
    public <TException extends Throwable> void add(TException exception, int retries, long delay) {
        var exceptionName = exception.getClass().getName();
        registry.put(
                exceptionName,
                new RegistryEntry(exceptionName, delay, retries));
    }

    @Override
    public <TException extends Throwable> RegistryEntry EntryFor(TException exception) {
        var exceptionName = exception.getClass().getName();
        if(registry.containsKey(exceptionName)) return registry.get(exceptionName);
        return RegistryEntry.Default(exception);
    }
}
