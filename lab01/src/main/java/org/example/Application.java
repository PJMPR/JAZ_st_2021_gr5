package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class<sampleClass> clazz = sampleClass.class;
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).toList();


    }
}
