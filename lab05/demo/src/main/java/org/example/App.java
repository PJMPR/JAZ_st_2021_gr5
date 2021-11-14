package org.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;

public class App {

    public static void main(String[] args){
        Logger LOGGER = Logger.getLogger(App.class);

        LOGGER.log(Level.INFO,"Hello friend!");


        SafeInvoker safeInvoker=new SafeInvoker();
        safeInvoker.invoke(() -> {BufferedReader rd = new BufferedReader(new FileReader("some_dir/file.txt"));});
        safeInvoker.invoke(() -> {int a=1/0;});
        UnsafeMethod unsafeMethod = new UnsafeMethod() {
            @Override
            public void execute(){
                String o=null;
                String c = o.toLowerCase(Locale.ROOT);
            }
        };
        safeInvoker.invoke(unsafeMethod);



    }



}
