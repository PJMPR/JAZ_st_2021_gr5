package org.example.validators;


import org.example.annotations.NotNull;

import java.lang.reflect.Field;

public class Validator {

    public <TClass> ValidationResult validate(TClass object) {
        Field[] fields = object.getClass().getDeclaredFields();


        for (int i = 0; i < fields.length; i++){
            Field f = fields[i];
            if (f.isAnnotationPresent(NotNull.class)){
//                nullFields[i] = f.getAnnotation(NotNull.class).message();
            }
        }
    }
}
