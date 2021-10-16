package org.example.validators;
import org.example.annotations.NotNull;
import org.example.annotations.Regex;
import org.example.annotations.Range;
import java.lang.reflect.Field;
import java.util.*;

public class Validator {

    public <TClass> ValidationResult validate(TClass object){
        ValidationResult value = new ValidationResult();
        value.setValid(true);
        value.setValidatedObject(object);
        for (Field f : object.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            List<String> erList = new ArrayList<>();
            if (f.isAnnotationPresent(Regex.class)){
                try {
                    if (!f.get(object).toString().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
                        erList.add(f.getAnnotation(Regex.class).message());
                        value.setValid(false);

                    }
                } catch (IllegalAccessException exception) {
                    exception.printStackTrace();
                }
            }
            if (f.isAnnotationPresent(NotNull.class)) {
                try {
                    if (Objects.isNull(f.get(object))) {
                        erList.add(f.getAnnotation(NotNull.class).msg1());
                        erList.add(f.getAnnotation(NotNull.class).msg2());
                        value.setValid(false);

                    }
                } catch (IllegalAccessException exception) {
                    exception.printStackTrace();
                }
            }
            if (f.isAnnotationPresent(Range.class)) {
                try {
                    int number = (int) f.get(object);
                    if (!(number > 0 && number < 10)) {
                        erList.add(f.getAnnotation(Range.class).msg());
                        value.setValid(false);

                    }
                } catch (IllegalAccessException exception) {
                    exception.printStackTrace();
                }
            }
                if (!erList.isEmpty()) {
                    value.getNotValidFields().put(f.getName(), erList);
                }
            }

        return value;
    }
}