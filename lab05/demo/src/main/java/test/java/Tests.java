package test.java;

import org.example.SafeInvoker;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class Tests {
    SafeInvoker safeInvoker = new SafeInvoker();

    @Test
    public void CheckAnyExeption(){
        safeInvoker.invoke(() ->{throw new Exception();});
    }

    @Test
    public void CheckThrowRuntimeExeption(){
        safeInvoker.invoke(() ->{throw new RuntimeException();});
    }

    @Test
    public void CheckThrowTimeoutException(){
        safeInvoker.invoke(() ->{throw new TimeoutException();});
    }

    @Test
    public void CheckThrowSQLException(){
        safeInvoker.invoke(() ->{throw new SQLException();});
    }

    @Test
    public void CheckThrowFileNotFoundException(){
        safeInvoker.invoke(() ->{throw new FileNotFoundException();});
    }

    @Test
    public void CheckThrowClassNotFoundException(){
        safeInvoker.invoke(() ->{throw new ClassNotFoundException();});
    }

}