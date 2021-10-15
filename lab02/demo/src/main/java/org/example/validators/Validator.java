package org.example.validators;


import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public <TClass> ValidationResult validate(TClass object){
        List<Field> declaredFields = Arrays.stream(object.getClass().getDeclaredFields()).toList();
        List<String> annotationsList = Arrays.stream([NotNull, @Range, @Regex])
        Map<String, List<Annotation>> objectsAnnotations = new HashMap<>();
        for (Field field : declaredFields) {
            for
            if (field.isAnnotationPresent(NotNull.class)) {
                objectsAnnotations.put(field.getName(), field.getAnnotation(NotNull.class));
            }



        }
    }
}
