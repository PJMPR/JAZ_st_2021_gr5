package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

public class Validator {

    public <TClass> ValidationResult validate(TClass object){
        ValidationResult validationResult = new ValidationResult();

        for (Field field: object.getClass().getDeclaredFields()) {
            try {
                List<String> errors = new ArrayList<>();
                field.setAccessible(true);
                if(field.isAnnotationPresent(NotNull.class)) {
                    if ((field.get(object) == null)) {
                        System.out.println(object);
                        String error = field.getAnnotation(NotNull.class).errorMessage();
                        errors.add(error);
                        validationResult.setValid(false);
                    }
                }

                if(field.isAnnotationPresent(Regex.class)) {
                    String pattern = field.getAnnotation(Regex.class).pattern();
                    Object objectValue = field.get(object);
                    if(!((String) objectValue).matches(pattern)) {
                        String error = field.getAnnotation(Regex.class).errorMessage();
                        errors.add(error);
                        validationResult.setValid(false);
                    }
                }

                if(field.isAnnotationPresent(Range.class)) {
                    int min = field.getAnnotation(Range.class).min();
                    int max = field.getAnnotation(Range.class).max();
                    int fieldValue = field.getInt(object);

                    if (fieldValue < min || fieldValue > max) {
                        String error = String.format("number is out of range [" + min + "," + max + "]");
                        errors.add(error);
                        validationResult.setValid(false);
                    }
                }

                if (!errors.isEmpty()) {
                    validationResult.setValid(true);
                }

                validationResult.setValidatedObject(object);
                validationResult.putNonValidField(field.getName(), errors);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return validationResult;
    }
}
