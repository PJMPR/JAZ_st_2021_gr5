package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){

        Method[] methods = clazz.getMethods();
        List<Method> getters = null;

        for (Method method : methods) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                if (Modifier.isPublic(method.getModifiers())) {
                    if (method.getParameterTypes().length == 0) {
                        assert false;
                        getters.add(method);
                    }
                }
            }
        }
        return getters;
        return Arrays.stream(clazz.getDeclaredMethods()).toList();
    }


    public List<Method> getPublicSetters(Class<?> clazz){

        Method[] methods = clazz.getMethods();
        List<Method> setters = null;

        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                if (Modifier.isPublic(method.getModifiers())) {
                    if (method.getParameterTypes().length == 0) {
                        assert false;
                        setters.add(method);
                    }
                }
            }
        }
        return setters;

        return Arrays.stream(clazz.getDeclaredMethods()).toList();
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }


}
