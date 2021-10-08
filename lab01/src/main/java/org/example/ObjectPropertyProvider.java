package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){
        Method[] methods = clazz.getMethods();
        List<Method> publicGetters = new ArrayList<>();
        for(Method method : methods){
            if(isGetter(method)){
                publicGetters.add(method);
            }
        }
        return publicGetters.stream().toList();
    }


    public List<Method> getPublicSetters(Class<?> clazz){
        Method[] methods = clazz.getMethods();
        List<Method> publicSetters = new ArrayList<>();
        for(Method method : methods){
            if(isSetter(method)){
                publicSetters.add(method);
            }
        }
        return publicSetters.stream().toList();
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        return Arrays.stream(clazz.getDeclaredFields()).toList();

    }
    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get") && !method.getName().startsWith("is")) return false;
        if(method.getParameterTypes().length != 0)   return false;
        if(method.getReturnType().equals(Void.TYPE)) return false;
        return true;
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        if(!method.getReturnType().equals(Void.TYPE)) return false;
        return true;
    }


}
