package org.example;

import org.example.observators.Subject;
import org.example.observators.Subscriber;

import java.lang.reflect.Field;

public class Application {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        System.out.println("adam@wp.pl".matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"));

        Sample obj = new Sample();
        Field f = Sample.class.getDeclaredField("name");
        f.setAccessible(true);
        System.out.println(
        f.get(obj)==null);


        Subject subject =new Subject();
        subject.register(number -> System.out.println(number));
        subject.emit(10);
    }
}



class Sample{ private String name;}