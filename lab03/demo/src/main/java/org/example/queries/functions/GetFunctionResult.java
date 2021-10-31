package org.example.queries.functions;

import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;

import java.lang.reflect.Field;
import java.util.List;


public class GetFunctionResult {

    public FunctionResult getFunctionResultForSumOrAverage(FunctionResult functionResult, Results results) {

        double sum = returnSum(functionResult, results);

        if(functionResult.getFunction() == Funcs.SUM) {
            functionResult.setValue(sum);
        }
        else if (functionResult.getFunction() == Funcs.AVARAGE) {
            functionResult.setValue(sum / results.getItems().size());
        }

        return functionResult;
    }

    private double returnSum(FunctionResult functionResult, Results results) {
        String fieldString = functionResult.getFieldName();
        List<Person> personList = results.getItems();

        double sum = 0.0;

        try {
            Field personField = Person.class.getField(fieldString);
            for (Person person : personList) {
                try {
                    personField.setAccessible(true);
                    sum += (double) personField.get(person);
                }
                catch (IllegalAccessException ex) {
                    return 0;
                }
            }
        } catch (NoSuchFieldException e) {
            return 0;
        }

        return sum;
    }
}
