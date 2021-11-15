package org.example.tests;
import org.junit.Test;

import org.example.SafeInvoker;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class TestSafeInvoker {
    SafeInvoker safeInvoker = new SafeInvoker();

    @Test
    public void shouldNotThrowClassNotFoundException(){
        safeInvoker.invoke(() -> {throw new ClassNotFoundException();});
    }

    @Test
    public void shouldNotThrowTimeoutException(){
        safeInvoker.invoke(() ->{throw new TimeoutException();});
    }

    @Test
    public void shouldNotThrowFileNotFoundException(){
        safeInvoker.invoke(() ->{throw new FileNotFoundException();});
    }

    @Test
    public void shouldNotThrowSQLException(){
        safeInvoker.invoke(() ->{throw new SQLException();});
    }

    @Test
    public void shouldNotThrowAnyException(){
        safeInvoker.invoke(() ->{throw new Exception();});
    }
}
