package org.example.validators;


import org.example.annotations.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


public class Validator {

    public <TClass> ValidationResult validate(TClass object) {

        List<Field> declaredFields;
        declaredFields = Arrays.stream(object.getClass().getDeclaredFields()).
                collect(Collectors.toList());

        ValidationResult result = new ValidationResult();
        result.setValidatedObject(object);
        result.setNotValidFields(returnNotValidFields(declaredFields, object));

        if(result.getNotValidFields().isEmpty()) {
            result.setValid(true);
        }

        return result;
    }

    private Map<String, List<String>> returnNotValidFields(List<Field> declaredFields, Object object) {
        Map<String, List<String>> notValidFields = new HashMap<>();

        for (Field field : declaredFields) {
            List<String> errors = new ArrayList<>();
            field.setAccessible(true);

            try {
                errors = NotNullAnnotation(object, field, errors);
                errors = RegexAnnotation(object, field, errors);
                errors = RangeAnnotation(object, field, errors);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if(!errors.isEmpty()) {
                notValidFields.put(field.getName(), errors);
            }
        }

        return notValidFields;
    }

    private List<String> NotNullAnnotation(Object object, Field field,
                                                        List<String> fieldErrors)
                                                        throws IllegalAccessException {

        if (field.isAnnotationPresent(NotNull.class)) {
            Object value = field.get(object);
            NotNull notNull = field.getAnnotation(NotNull.class);

            if (value == null) {
                fieldErrors.add(notNull.message());
                fieldErrors.add("field is empty");
            }
        }

        return fieldErrors;
    }

    private List<String> RegexAnnotation(Object object, Field field,
                                           List<String> fieldErrors)
            throws IllegalAccessException {

        if (field.isAnnotationPresent(Regex.class)) {
            Object value = field.get(object);
            Regex regex = field.getAnnotation(Regex.class);

            if (!value.toString().matches(regex.pattern())) {
                fieldErrors.add(regex.message());
            }
        }

        return fieldErrors;
    }

    private List<String> RangeAnnotation(Object object, Field field,
                                         List<String> fieldErrors)
            throws IllegalAccessException {

        if (field.isAnnotationPresent(Range.class)) {
            Object value = field.getInt(object);
            Range range = field.getAnnotation(Range.class);

            if ((int)value > range.max() || (int)value < range.min()) {
                fieldErrors.add("number is out of range [" + range.min() + "," + range.max() + "]");
            }
        }

        return fieldErrors;
    }
}
