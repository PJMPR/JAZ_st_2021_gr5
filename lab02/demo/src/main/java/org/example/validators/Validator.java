package org.example.validators;


import org.example.validators.rules.NotNullValidationRule;
import org.example.validators.rules.RangeValidationRule;
import org.example.validators.rules.RegexValidationRule;
import org.example.validators.rules.ValidationRule;

import java.util.List;

public class Validator {

    List<ValidationRule> rules = List.of(
      new RangeValidationRule(),
      new NotNullValidationRule(),
      new RegexValidationRule()
    );

    public <TClass> ValidationResult validate(TClass object){
        ValidationResult result = new ValidationResult();
        result.setValid(true);
        result.setValidatedObject(object);
        rules.stream().forEach(rule->rule.Validate(result));
        return result;
    }
}
