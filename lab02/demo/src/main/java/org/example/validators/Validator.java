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

        result.getNotValidFields().put("name",new ArrayList<String>());
        result.getNotValidFields().get("name").add("message");

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
            if(field.get(object) == null){
                if(result.getNotValidFields().containsKey(field.getName())){
                    result.getNotValidFields().get(field.getName()).add(field.getAnnotation(NotNull.class).message());
                }else {
                    result.getNotValidFields().put(field.getName(),new ArrayList<String>());
                    result.getNotValidFields().get(field.getName()).add(field.getAnnotation(NotNull.class).message());
                }
                result.setValid(false);
            }
        }

        for(Field f : regexFields){
            if(!f.get(object).toString().matches(f.getAnnotation(Regex.class).pattern())){
                result.setValid(false);
            }
        }

        for (Field f : rangeFields) {
            int max = f.getAnnotation(Range.class).max();
            int min = f.getAnnotation(Range.class).min();

            int objVal = (Integer)f.get(object);

            if(objVal > max || objVal<min ){
                result.setValid(false);
            }
        }

    }
}
