package org.example.validators;


import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Validator {


    public <TClass> ValidationResult validate(TClass object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        Field[] notNullFields = new Field[fields.length];
        Field[] regexFields = new Field[fields.length];
        Field[] rangeFields = new Field[fields.length];

        ValidationResult result = new ValidationResult();
        result.setValidatedObject(object);
        result.setValid(true);


        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            if (f.isAnnotationPresent(NotNull.class)) {
                notNullFields[i] = f;
            } else if (f.isAnnotationPresent(Regex.class)) {
                regexFields[i] = f;
            } else if (f.isAnnotationPresent(Range.class)) {
                rangeFields[i] = f;
            }
        }

        for (Field field : notNullFields) {
            if (field.get(object) == null) {
                if (!result.getNotValidFields().containsKey(field.getName())) {
                    result.getNotValidFields().put(field.getName(), new ArrayList<String>());
                }
                result.getNotValidFields().get(field.getName()).add(field.getAnnotation(NotNull.class).message());
                result.setValid(false);
            }
        }

        for (Field field : regexFields) {
            if (!field.get(object).toString().matches(field.getAnnotation(Regex.class).pattern())) {
                if (!result.getNotValidFields().containsKey(field.getName())) {
                    result.getNotValidFields().put(field.getName(), new ArrayList<String>());
                }
                result.getNotValidFields().get(field.getName()).add(field.getAnnotation(Regex.class).message());
                result.setValid(false);
            }
        }

        for (Field field : rangeFields) {
            int max = field.getAnnotation(Range.class).max();
            int min = field.getAnnotation(Range.class).min();

            int objVal = (Integer) field.get(object);

            if (objVal > max || objVal < min) {
                if (!result.getNotValidFields().containsKey(field.getName())) {
                    result.getNotValidFields().put(field.getName(), new ArrayList<String>());
                }
                result.getNotValidFields().get(field.getName()).add("number is out of range ["+min+","+max+"]");
                result.setValid(false);
            }
        }
        return result;
    }
}
