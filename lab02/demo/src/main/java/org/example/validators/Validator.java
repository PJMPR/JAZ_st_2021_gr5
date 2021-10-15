package org.example.validators;


import org.example.annotations.NotNull;

import java.lang.reflect.Field;

public class Validator {

    public <TClass> ValidationResult validate(TClass object) throws NoSuchFieldException, IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        fields[0].get(object);
        for (Field field : fields){
            if (field.get(object) == null){ //isAnnotationPresent trzeba ogarnac

            }


        }
        return new ValidationResult();
    }
}
