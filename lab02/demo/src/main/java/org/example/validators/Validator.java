package org.example.validators;

import org.example.annotations.NotNull;
import org.example.annotations.Regex;
import org.example.annotations.Range;

import java.lang.reflect.Field;
import java.util.*;

public class Validator {

    public <TClass> ValidationResult validate(TClass object) {
        ValidationResult result = new ValidationResult();
        result.setValidatedObject(object);
        result.setValid(true);

        for (Field field : object.getClass().getDeclaredFields()) {
            List<String> errors = new ArrayList<>();
            field.setAccessible(true);

            //if @Range
            if (field.isAnnotationPresent(Range.class)) {
                try {
                    int i = (int) field.get(object);
                    int min = field.getDeclaredAnnotation(Range.class).min();
                    int max = field.getDeclaredAnnotation(Range.class).max();

                    if (!(i > min && i < max)) {
                        errors.add(field.getAnnotation(Range.class).message());
                        result.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            //if @Regex
            if (field.isAnnotationPresent(Regex.class)) {
                try {
                    if (!field.get(object).toString().matches(field.getDeclaredAnnotation(Regex.class).pattern())) {
                        errors.add(field.getAnnotation(Regex.class).message());
                        result.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            //if @NotNull
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    if (field.get(object) == null) {
                        errors.add(field.getAnnotation(NotNull.class).message());
                        errors.add(field.getAnnotation(NotNull.class).message2());
                        result.setValid(false);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (!errors.isEmpty()) {
                result.getNotValidFields().put(field.getName(), errors);
            }
        }

        return result;
    }
}
