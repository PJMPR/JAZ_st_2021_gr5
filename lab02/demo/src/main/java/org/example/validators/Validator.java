package org.example.validators;



import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Validator {

    ValidationResult validationResult = new ValidationResult();

    public <TClass> ValidationResult validate(TClass object) throws IllegalAccessException {
        List<String> errors = new ArrayList<>();
        Field[] list = object.getClass().getDeclaredFields();
        validationResult.setValidatedObject(object);

        for (Field field : list) {
            field.setAccessible(true);
            Object obj = field.get(object);

            if (obj == null) {
                    validationResult.setValid(false);
                    errors.add("field is null");
                    errors.add("field is empty");
                    System.out.println(errors);
                    validationResult.getNotValidFields().put(field.getName(), errors);
            }
                if (field.isAnnotationPresent(Regex.class)) {
                    if (!field.get(object).toString().matches(field.getAnnotation(Regex.class).pattern())) {
                        errors.add("email should be in correct format");
                        validationResult.getNotValidFields().put(field.getName(), errors);
                        System.out.println(errors);
                    }
                } else if (field.isAnnotationPresent(Range.class)) {
                    Object number = field.get(object);
                    if (!((int)number <= field.getAnnotation(Range.class).max() && (int)number >= field.getAnnotation(Range.class).min())) {
                        int min = field.getAnnotation(Range.class).min();
                        int max = field.getAnnotation(Range.class).max();
                        errors.add("number is out of range [" + min + "," + max + "]");
                        validationResult.getNotValidFields().put(field.getName(), errors);
                        System.out.println(errors);
                    }
                }
            }
        System.out.println(validationResult.getNotValidFields().keySet());
        return validationResult;
    }
}
