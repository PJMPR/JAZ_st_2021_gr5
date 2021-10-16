package org.example.validators;


import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;

import java.lang.reflect.Field;
import java.util.*;


public class Validator {


    public <TClass> ValidationResult validate(TClass object) throws IllegalAccessException {
        Map<String, List<String>> notValidFields = new HashMap<>();
        ValidationResult validationResult = new ValidationResult(object, true, notValidFields);
        Field[] fieldList = object.getClass().getDeclaredFields();
        for (int i = 0; i < fieldList.length; i++) {
            Field f =fieldList[i];
            if (f.isAnnotationPresent(NotNull.class)) {
                if (f.get(object) == null) {
                    validationResult.setValid(false);
                    notValidFields.put(f.getName(), new ArrayList<>());
                    notValidFields.get(f.getName()).add(f.getAnnotation(NotNull.class).message());
                    notValidFields.get(f.getName()).add(f.getAnnotation(NotNull.class).message2());
                }
            }
            if (f.isAnnotationPresent(Range.class)) {
                if ((int) f.get(object) < f.getAnnotation(Range.class).min()
                        || (int) f.get(object) > f.getAnnotation(Range.class).max()) {
                    validationResult.setValid(false);
                    if (notValidFields.containsKey(f.getName())) {
                        notValidFields.get(f.getName()).add(f.getAnnotation(Range.class).message());
                    } else {
                        notValidFields.put(f.getName(), new ArrayList<>());
                        notValidFields.get(f.getName()).add(f.getAnnotation(Range.class).message());
                    }
                }
            }
            if (f.isAnnotationPresent(Regex.class)) {
                if (!((String) f.get(object)).matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
                    validationResult.setValid(false);
                    if (notValidFields.containsKey(f.getName())) {
                        notValidFields.get(f.getName()).add(f.getAnnotation(Regex.class).message());
                    } else {
                        notValidFields.put(f.getName(), new ArrayList<>());
                        notValidFields.get(f.getName()).add(f.getAnnotation(Regex.class).message());
                    }
                }
            }

        }


        return validationResult;
    }

}
