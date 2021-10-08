package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz) {
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Method> getters = new java.util.ArrayList<>(Collections.emptyList());


        for (Method m : methods) {
            String methodName = m.getName();
            if (Modifier.isPublic(m.getModifiers())) {
                if (methodName.startsWith("get") || methodName.startsWith("is")) {
                    if (m.getParameterCount() == 0) {
                        if(!void.class.equals(m.getReturnType())){
                            getters.add(m);
                        }
                    }
                }
            }
        }

        return getters;
    }


    public List<Method> getPublicSetters(Class<?> clazz) {
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();
        List<Method> setters = new java.util.ArrayList<>(Collections.emptyList());


        for (Method m : methods) {
            String methodName = m.getName();
            if (Modifier.isPublic(m.getModifiers())) {
                if (methodName.startsWith("set")) {
                    if (m.getParameterCount() == 1) {
                        if(void.class.equals(m.getReturnType())){
                            setters.add(m);
                        }
                    }
                }
            }
        }

        return setters;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).toList();
    }


}
