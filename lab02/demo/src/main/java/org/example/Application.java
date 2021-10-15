package org.example;

import org.example.validators.Validator;

public class Application {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        System.out.println("adam@wp.pl".matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"));

        Sample obj = new Sample();
        System.out.println(
        Sample.class.getDeclaredField("name").get(obj)==null);

    }
}

class Sample{ String name;}