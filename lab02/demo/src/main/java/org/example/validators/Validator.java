package org.example.validators;


import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public <TClass> ValidationResult validate(TClass object){
        ValidationResult result = new ValidationResult();
        result.setValidatedObject(object);
        Field[] fields = object.getClass().getDeclaredFields();
        Pattern pattern = Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$");
        result.setValid(true);
        for (Field field : fields){
            boolean isFieldValid = true;
            field.setAccessible(true);
            List<String> notValidMessages = new ArrayList<>();
            if (field.isAnnotationPresent(NotNull.class)){
                try {
                    if(field.get(object) == null){
                        notValidMessages.add(field.getAnnotation(NotNull.class).message());
                        notValidMessages.add(field.getAnnotation(NotNull.class).message2());
                        result.setValid(false);
                        isFieldValid = false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if(field.isAnnotationPresent(Regex.class)){
                Matcher matcher = null;
                try {
                    matcher = pattern.matcher(field.get(object).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                boolean matchFound = matcher.matches();
                if (!matchFound){
                    notValidMessages.add(field.getAnnotation(Regex.class).message());
                    result.setValid(false);
                    isFieldValid = false;
                }
            }
            if (field.isAnnotationPresent(Range.class)){
                int i = 0;
                try {
                    i = (int) field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if(i>10 || i<0){
                    notValidMessages.add(field.getAnnotation(Range.class).message());
                    result.setValid(false);
                    isFieldValid = false;
                }
            }
            if(!isFieldValid){
                result.addNotValidFields(field.getName(),notValidMessages);
            }
        }
        return result;
    }
}
