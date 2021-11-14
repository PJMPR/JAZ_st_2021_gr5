package org.example;

import org.apache.log4j.*;


import java.io.*;


public class App {
    public static void main(String[] args) throws IOException {
//        Logger LOGGER = Logger.getLogger(App.class);
//        BasicConfigurator.configure(new FileAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"),"logs.txt"));


       // LOGGER.log(Level.ERROR,"Some info");

        SafeInvoker safeInvoker = new SafeInvoker();
        safeInvoker.invoke(()->{throw new FileNotFoundException();});
    }
}