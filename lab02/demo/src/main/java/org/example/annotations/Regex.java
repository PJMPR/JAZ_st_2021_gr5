package org.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Regex {
    String pattern() default "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    String message() default "Regex error";
}
