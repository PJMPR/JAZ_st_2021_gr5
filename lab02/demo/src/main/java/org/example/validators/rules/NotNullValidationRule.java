package org.example.validators.rules;

import org.example.annotations.NotNull;
import org.example.validators.ValidationResult;

import java.lang.reflect.Field;

public class NotNullValidationRule extends ValidationRule {

    @Override
    protected String getValidationMessage(Field field) {
        return field.getAnnotation(NotNull.class).message();
    }

    @Override
    protected boolean fulfillCondition(Field field, ValidationResult validationResult) throws IllegalAccessException {
        return field.get(validationResult.getValidatedObject()) != null;
    }

    @Override
    protected boolean hasAnnotation(Field field) {
        return field.isAnnotationPresent(NotNull.class);
    }
}
