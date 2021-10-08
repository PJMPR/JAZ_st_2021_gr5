package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {


    public List<Method> getPublicGetters(Class<?> clazz){
        Method x = Arrays.stream(clazz.getDeclaredMethods()).findFirst().or(null).get();
        for

        return Arrays.stream(clazz.getDeclaredMethods()).toList();
    }


    public List<Method> getPublicSetters(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredMethods()).toList();
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}


