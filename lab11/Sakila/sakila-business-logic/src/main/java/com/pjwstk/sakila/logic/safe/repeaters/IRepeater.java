package com.pjwstk.sakila.logic.safe.repeaters;

public interface IRepeater {
    <TException extends Throwable> IRepeater For(TException exception);
    void retry();
    boolean shouldRetry();
    IRepeater waiting();
}
