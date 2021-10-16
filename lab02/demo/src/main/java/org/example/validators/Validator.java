package org.example.validators;


import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.*;

public class Validator {

    public <TClass> ValidationResult validate(TClass object) {
        ValidationResult result = new ValidationResult();
        List<String> errors = new ArrayList<>();
        result.setValidatedObject(object);
        for(Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            //check#1
            if(field.isAnnotationPresent(NotNull.class)){
                try{
                    if(field.get(object) == null) {
                        errors.add(field.getAnnotation(NotNull.class).error1());
                        errors.add(field.getAnnotation(NotNull.class).error2());
                    }
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            //check#2
            if(field.isAnnotationPresent(Range.class)){
                try{
                    int i = (Integer)field.get(object);
                    if(i < 0 || i > 10)
                        errors.add(field.getAnnotation(Range.class).message());
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            //check#3
            if(field.isAnnotationPresent(Regex.class)){
                try{
                    if(!field.get(object).toString().matches(field.getDeclaredAnnotation(Regex.class).pattern()))
                        errors.add(field.getAnnotation(Regex.class).message());
                } catch(IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if(errors.size()!=0) {
                result.getNotValidFields().put(field.getName(), new ArrayList<>(errors));
                errors.clear();
                result.setValid(false);
            }
        }
        return result;
    }
}