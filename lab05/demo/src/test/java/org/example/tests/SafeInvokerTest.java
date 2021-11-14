package org.example.tests;

import org.example.SafeInvoker;
import org.junit.Test;

import javax.naming.ConfigurationException;
import javax.naming.ContextNotEmptyException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class SafeInvokerTest {
    SafeInvoker safeInvoker = new SafeInvoker();

    @Test
    public void SafeInvokerShouldNotThrowFileNotFoundException(){
        safeInvoker.invoke(() ->{throw new FileNotFoundException();});
    }

    @Test
    public void SafeInvokerShouldNotThrowClassNotFoundException(){
        safeInvoker.invoke(() ->{throw new ClassNotFoundException();});
    }

    @Test
    public void SafeInvokerShouldNotThrowSQLException(){
        safeInvoker.invoke(() ->{throw new SQLException();});
    }

    @Test
    public void SafeInvokerShouldNotThrowTimeoutException(){
        safeInvoker.invoke(() ->{throw new TimeoutException();});
    }

    @Test
    public void SafeInvokerShouldNotThrowAnyException(){
        safeInvoker.invoke(() ->{throw new Exception();});
        safeInvoker.invoke(() ->{throw new ContextNotEmptyException();});
        safeInvoker.invoke(() ->{throw new ConfigurationException();});
        safeInvoker.invoke(() ->{throw new SecurityException();});
        safeInvoker.invoke(() ->{throw new RuntimeException();});
    }
}
