package org.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class App {

    public static void main(String[] args) {
        Logger LOGGER = Logger.getLogger(App.class);
        LOGGER.log(Level.INFO, "Hello friend!");
    }
}
