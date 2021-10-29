package org.example.validators.rules;

import org.example.validators.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class ValidationRule {


    public void Validate(ValidationResult validationResult){
        Arrays.stream(validationResult.getValidatedObject().getClass().getDeclaredFields())
                .filter(field -> hasAnnotation(field))
                .forEach(field->check(field, validationResult));
    }


    private void check(Field field, ValidationResult validationResult) {
        field.setAccessible(true);
        try {
            if(fulfillCondition(field, validationResult)) return;
            validationResult.setValid(false);
            addMessage(validationResult.getNotValidFields(), field);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addMessage(Map<String, List<String>> notValidFields, Field field) {
        String fieldName = field.getName();
        if(!notValidFields.containsKey(fieldName))
            notValidFields.put(fieldName, new ArrayList<String>());
        notValidFields.get(fieldName).add(getValidationMessage(field));
    }

    protected abstract String getValidationMessage(Field field);
    protected abstract boolean fulfillCondition(Field field, ValidationResult validationResult) throws IllegalAccessException;
    protected abstract boolean hasAnnotation(Field field);
}
