import org.example.SafeInvoker;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class SafeInvokerTest {
    SafeInvoker safeInvoker = new SafeInvoker();

    @Test
    public void shouldNotThrowFileNotFoundException(){
        safeInvoker.invoke(() ->{throw new FileNotFoundException();});
    }

    @Test
    public void shouldNotThrowClassNotFoundException(){
        safeInvoker.invoke(() ->{throw new ClassNotFoundException();});
    }

    @Test
    public void shouldNotThrowSQLException(){
        safeInvoker.invoke(() ->{throw new SQLException();});
    }

    @Test
    public void shouldNotThrowTimeoutException(){
        safeInvoker.invoke(() ->{throw new TimeoutException();});
    }

    @Test
    public void shouldNotThrowAnyException(){
        safeInvoker.invoke(() ->{throw new Exception();});
        safeInvoker.invoke(() ->{throw new RuntimeException();});
    }
}