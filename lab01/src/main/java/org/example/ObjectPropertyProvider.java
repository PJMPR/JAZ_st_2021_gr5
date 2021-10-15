package org.example;


import javax.security.auth.Subject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz) {
        List<Method> getters = new ArrayList<>();
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();

        for (Method method : methods) {
            if (isGetter(method)) {
                getters.add(method);
            }
        }
        return getters;
    }

    public static boolean isGetter(Method method) {
        if (Modifier.isPublic(method.getModifiers())) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                if (method.getParameterCount() == 0) {
                    if (!void.class.equals(method.getReturnType())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public List<Method> getPublicSetters(Class<?> clazz) {
        List<Method> setters = new ArrayList<>();
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();

        for (Method method : methods) {
            if (isSetter(method)) {
                setters.add(method);
            }
        }
        return setters;
    }

    public static boolean isSetter(Method method) {
        if (Modifier.isPublic(method.getModifiers())) {
            if (method.getName().startsWith("set")) {
                if (method.getParameterCount() == 1) {
                    if (void.class.equals(method.getReturnType())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        List<Field> fieldsAll = Arrays.stream(clazz.getDeclaredFields()).toList();
        List<Method> gettersAll = getPublicGetters(clazz);
        List<Method> settersAll = getPublicSetters(clazz);

        for (Method getters : gettersAll) {
            for (Field field : fieldsAll) {
                if (getters.getName().toLowerCase().contains(field.getName().toLowerCase())) {
                    fields.add(field);
                    break;
                }
            }
        }

        for (Method setters : settersAll) {
            for (Field field : fieldsAll) {
                if (setters.getName().toLowerCase().contains(field.getName().toLowerCase()) && fields.contains(field.getName().toLowerCase())) {
                    fields.add(field);
                    break;
                }
            }
        }
        for (Field field : fields) {
            System.out.println(field);
            }
        return fields;
    }

}