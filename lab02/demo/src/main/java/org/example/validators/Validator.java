package org.example.validators;


import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {


    public <TClass> ValidationResult validate(TClass object) throws IllegalAccessException {
        ValidationResult result = new ValidationResult();
        result.setValidatedObject(object);
        result.setValid(true);


        ArrayList<Field> notNullFields = new ArrayList<>();
        ArrayList<Field> regexFields = new ArrayList<>();
        ArrayList<Field> rangeFields = new ArrayList<>();

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field f : fields) {

            f.setAccessible(true);

            if (f.isAnnotationPresent(NotNull.class)) {
                notNullFields.add(f);
            }
            if (f.isAnnotationPresent(Regex.class)) {
                regexFields.add(f);
            }
            if (f.isAnnotationPresent(Range.class)) {
                rangeFields.add(f);
            }
        }

        for (Field field : notNullFields) {

            if (field.get(object) == null) {
                if (!result.getNotValidFields().containsKey(field.getName())) {
                    result.getNotValidFields().put(field.getName(), new ArrayList<String>());
                }
                result.getNotValidFields().get(field.getName()).add(field.getAnnotation(NotNull.class).message());
                result.getNotValidFields().get(field.getName()).add(field.getAnnotation(NotNull.class).message2());
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
                result.getNotValidFields().get(field.getName()).add("number is out of range [" + min + "," + max + "]");
                result.setValid(false);
            }
        }
        return result;
    }
}
