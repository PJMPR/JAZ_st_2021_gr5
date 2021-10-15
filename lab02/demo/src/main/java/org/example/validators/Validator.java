package org.example.validators;


import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.*;

public class Validator {

    public <TClass> ValidationResult validate(TClass object) {
        ValidationResult result = new ValidationResult();
        List<String> errList = new ArrayList<>();

        result.setValidatedObject(object);
        result.setValid(true);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(NotNull.class)){
                try {
                    if(Objects.equals(field.get(object), null)) {
                        errList.add(field.getAnnotation(NotNull.class).messageNull());
                        errList.add(field.getAnnotation(NotNull.class).messageEmpty());
                    }
                    else if(Objects.equals(field.get(object), ""))
                        errList.add(field.getAnnotation(NotNull.class).messageEmpty());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Regex.class)){
                try {
                    if(!field.get(object).toString().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"))
                        errList.add(field.getAnnotation(Regex.class).message());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Range.class)){
                try {
                    Integer num = (Integer)field.get(object);
                    if (num < 0 || num > 10)
                        errList.add(field.getAnnotation(Range.class).message());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if(errList.size() != 0) {
                result.getNotValidFields().put(field.getName(), new ArrayList<>(errList));
                errList.clear();
            }
        }


        if(result.getNotValidFields().size() != 0){
            result.setValid(false);
        }

        return result;
    }
}
