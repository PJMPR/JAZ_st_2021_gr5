package org.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {

    public static void main(String[] args){
        Logger LOGGER = Logger.getLogger(App.class);

        LOGGER.log(Level.INFO,"Hello friend!");

        UnsafeMethod unsafeMethod1 = () -> {
                BufferedReader rd = new BufferedReader(new FileReader("some_dir/file.txt"));

        };

        SafeInvoker safeInvoker=new SafeInvoker();
        safeInvoker.invoke(unsafeMethod1);

    }



}
