package org.example;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectPropertyProvider {

    public List<Method> getPublicGetters(Class<?> clazz){
        List<Method> list = Arrays.stream(clazz.getDeclaredMethods()).toList();
       List<Method> getters_list= new ArrayList<Method>();
        for (Method method:list) {
            if(method.getModifiers()== Modifier.PUBLIC){
                if(method.getName().startsWith("get")||method.getName().startsWith("is")){
                    if(method.getParameterCount()==0){
                        String name =method.getName();
                        String field_name = name.substring(3).toLowerCase();
                        String return_type= method.getReturnType().getTypeName().toString().toLowerCase();
                        if(field_name.equals(return_type)){
                            getters_list.add(method);
                        }
                    }

                }
            }
        }
        return getters_list;
    }


    public List<Method> getPublicSetters(Class<?> clazz){

        List<Method> list = Arrays.stream(clazz.getDeclaredMethods()).toList();

        List<Method> setters_list= new ArrayList<Method>();
        for (Method method:list) {
            if(method.getModifiers()== Modifier.PUBLIC){
                if(method.getName().startsWith("set")){
                    if(method.getParameterCount()==1){
                        if(method.getReturnType().equals(Void.TYPE)){
                            setters_list.add(method);
                        }
                    }
                }
            }
        }
        return setters_list;
    }


    public List<Field> getFieldsForPublicProperties(Class<?> clazz){

        List<Field> list = Arrays.stream(clazz.getDeclaredFields()).toList();
        List<Field> field_list = new ArrayList<>();

        //TODO
        return field_list;
    }




}
