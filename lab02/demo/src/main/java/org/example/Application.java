package org.example;


import org.example.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {

        System.out.println("adam@wp.pl".matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"));

    }
}
