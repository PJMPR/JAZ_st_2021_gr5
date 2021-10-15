package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.*;

public class Validator {

    public <TClass> ValidationResult validate(TClass object){
        ValidationResult result = new ValidationResult();
        result.setValidatedObject(object);
        result.setValid(true);
        ArrayList<String> errorList = new ArrayList<>();
        Class<?> clazz = object.getClass();

        Map<String, List<String>> notValidFields = new HashMap<String, List<String>>();
        for (Field field :
                clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Boolean notValid = false;
            if (field.isAnnotationPresent(NotNull.class)){
                try {
                    if(Objects.isNull(field.get(object))){
                        result.setValid(false);
                        notValid = true;
                        errorList.add(field.getDeclaredAnnotation(NotNull.class).message());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (field.isAnnotationPresent(Regex.class)){
                try {
                    String temp = (String) field.get(object);
                    if(!temp.matches(field.getDeclaredAnnotation(Regex.class).pattern())){
                        result.setValid(false);
                        notValid = true;
                        errorList.add(field.getDeclaredAnnotation(Regex.class).message());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (field.isAnnotationPresent(Range.class)){
                try {
                    int temp = (int) field.get(object);
                    int min = field.getDeclaredAnnotation(Range.class).min();
                    int max = field.getDeclaredAnnotation(Range.class).max();

                    if(temp < min || temp > max){
                        result.setValid(false);
                        notValid = true;
                        errorList.add("number is out of range [" + min + "," + max + "]");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if(notValid){
                notValidFields.put(field.getName(), errorList);
            }
        }

        result.setNotValidFields(notValidFields);
        return result;
    }
}