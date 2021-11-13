package org.example.exeptions;

import java.io.IOException;

public class IOExceptionThrow implements ExceptionThrower {

    @Override
    public void exception() throws IOException {
        throw new IOException();
    }
}
