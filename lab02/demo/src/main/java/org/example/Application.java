package org.example;

import org.example.annotations.NotNull;
import org.example.annotations.Range;
import org.example.annotations.Regex;
import org.example.validators.ValidationResult;
import org.example.validators.Validator;

public class Application {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Sample sample = new Sample("jan@.pl", 2, null);

        Validator validator = new Validator();
        ValidationResult validationResult = validator.validate(sample);
        if(!validationResult.isValid()) {
            System.out.println("Object is invalid");
            System.out.println(validationResult.getNotValidFields());
        }
    }
}

class Sample{
    @NotNull
    @Regex(pattern = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")
    String mail;

    @Range(min = 0, max = 10)
    int number;

    @NotNull
    String name;

    public Sample(final String mail, final int number, final String name) {
        this.mail = mail;
        this.number = number;
        this.name = name;
    }
}