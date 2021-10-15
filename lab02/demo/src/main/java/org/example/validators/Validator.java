package org.example.validators;


import org.example.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {

    @NotNull
    public String s;

    public <TClass> ValidationResult validate(TClass object) throws IllegalAccessException {
      ValidationResult validationResult=new ValidationResult();
      Field[] fieldList= object.getClass().getFields();
        for (Field f:fieldList) {
            System.out.println(f.isAnnotationPresent(NotNull.class));
            if(f.isAnnotationPresent(NotNull.class)){
                if(f.get(this)==null){
                    validationResult.setValid(false);
                    System.out.println("null");
                }else {
                    System.out.println("not null");
                }

            }

        }


        return null;
    }


    public static void main(String[] args)throws IllegalAccessException {
        Validator validator=new Validator();
        validator.s="a";
        validator.validate(validator);
    }
}
