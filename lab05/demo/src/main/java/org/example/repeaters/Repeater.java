package org.example.repeaters;

public class Repeater implements IRepeater{
    String exceptionName;
    int counter;
    int retryCount;
    int delayTime;
    IRepeaterExceptionRegistry exceptionRegistry;

    public Repeater(IRepeaterExceptionRegistry exceptionRegistry) {
        this.exceptionRegistry = exceptionRegistry;
    }

    @Override
    public <TException extends Throwable> IRepeater For(TException exception) {
        var exceptionName = exception.getClass().getName();
        if(exceptionName.equals(this.exceptionName))return this;

        var entry = exceptionRegistry.EntryFor(exception);
        retryCount = entry.retriesCount();
        delayTime=entry.delay();
        counter=1;
        this.exceptionName=exceptionName;
        return this;
    }

    @Override
    public void retry() {
        counter++;
    }

    @Override
    public boolean shouldRetry() {
        return counter<=retryCount;
    }

    @Override
    public IRepeater waiting() {
        try {
            Thread.sleep(delayTime*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
}
