package org.example.handlers;

public class IOExceptionHandler implements HandleException {
    @Override
    public String getHandleExceptionSimpleName() {
        return null;
    }

    @Override
    public boolean handleException(Throwable e) {
        System.out.println("Caught " + e);
        return true;
    }
}
