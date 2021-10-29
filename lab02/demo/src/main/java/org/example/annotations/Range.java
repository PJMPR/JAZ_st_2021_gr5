package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
    int min();

    int max();
    String message() default "number is out of range [%d,%d]";
}
