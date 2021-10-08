package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){
        List<Method> getters = new ArrayList<>();
        Method[] methods = clazz.getMethods();

        for(Method method : methods){
            if(isGetter(method)){
                getters.add(method);
            };
        }
        return Arrays.stream();
    }

    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))    return false;
        if(method.getParameterTypes().length != 0)   return false;
        return true;
    }


    public List<Method> getPublicSetters(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredMethods()).toList();
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
