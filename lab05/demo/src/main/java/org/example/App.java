package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.TimeoutException;

public class App {

    public static void main(String[] args) {
        SafeInvoker safeInvoker = new SafeInvoker();

        Supplier unsafeMethod1 = () ->{
            FileReader fr = new FileReader("somefile.txt");
        };

       // safeInvoker.invoke(unsafeMethod1);


        Supplier unsafeMethod2 = () ->{
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sonoo","root","root");
        };

        safeInvoker.invoke(unsafeMethod2);
    }
}
/*
TODO:
 Change ErrorHandler from interface to abstract class,
 Add default error handler,
 Write tests,
 Refactor
*/
