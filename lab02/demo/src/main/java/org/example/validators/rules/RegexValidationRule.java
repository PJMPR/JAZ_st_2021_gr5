package org.example.validators.rules;

import org.example.annotations.Regex;
import org.example.validators.ValidationResult;

import java.lang.reflect.Field;

public class RegexValidationRule extends ValidationRule {


    @Override
    protected String getValidationMessage(Field field) {
        return field.getAnnotation(Regex.class).message();
    }

    @Override
    protected boolean fulfillCondition(Field field, ValidationResult validationResult) throws IllegalAccessException {
        return ((String) field.get(validationResult.getValidatedObject())).matches(
                field.getAnnotation(Regex.class).pattern()
        );
    }

    @Override
    protected boolean hasAnnotation(Field field) {
        return field.isAnnotationPresent(Regex.class);
    }
}
