package com.pjwstk.sakila.logic.safe.repeaters;

public interface IRepeaterExceptionRegistry {
    <TException extends Throwable> void add(TException exception, int retries, long delay);
    <TException extends Throwable> RegistryEntry EntryFor(TException exception);



    public record RegistryEntry(String exceptionName, long delay, int retriesCount) {

        public static RegistryEntry Default(Throwable ex) {
            return new RegistryEntry(ex.getClass().getName(), 0, 0);
        }
    }
}
