package org.example.handlers;

public class FileNotFoundExceptionHandler implements HandleException {
    @Override
    public String getHandleExceptionSimpleName() {
        return "FileNotFoundException";
    }

    @Override
    public boolean handleException(Throwable e) {
        System.out.println("Caught " + e);
        return true;
    }
}
