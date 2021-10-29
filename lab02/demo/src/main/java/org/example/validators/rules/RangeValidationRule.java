package org.example.validators.rules;

import org.example.annotations.Range;
import org.example.validators.ValidationResult;

import java.lang.reflect.Field;

public class RangeValidationRule extends ValidationRule{
    @Override
    protected String getValidationMessage(Field field) {
        Range range = field.getAnnotation(Range.class);
        return range.message().formatted(range.min(), range.max());
    }

    @Override
    protected boolean fulfillCondition(Field field, ValidationResult validationResult) throws IllegalAccessException {
        Number number = (Number) field.get(validationResult.getValidatedObject());
        Range range = field.getAnnotation(Range.class);
        return range.min()<number.doubleValue() &&number.doubleValue()<range.max();
    }

    @Override
    protected boolean hasAnnotation(Field field) {
        return field.isAnnotationPresent(Range.class);
    }
}
