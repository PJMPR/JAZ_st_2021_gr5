package org.example.exeptions;

public class FileNotFoundExceptionThrow implements ExceptionThrower {

    @Override
    public void exception() throws NoSuchFieldException {
        throw new NoSuchFieldException();
    }
}

