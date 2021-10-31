package org.example.functions;

import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;

import java.util.stream.Collectors;

public class SumAndAverage {
    public FunctionResult sumAge(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("income");
        functionResult.setValue(
                results.getItems().stream()
                        .mapToInt(Person::getAge).sum());
        return functionResult;
    }

    public FunctionResult sumIncome(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("income");
        functionResult.setValue(
                results.getItems().stream()
                        .mapToDouble(Person::getIncome).sum());
        return functionResult;
    }

    public FunctionResult averageIncome(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("income");
        functionResult.setValue(results.getItems().stream()
                .collect(Collectors.averagingDouble(Person::getIncome)));
        return functionResult;
    }

    public FunctionResult averageAge(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("age");
        functionResult.setValue(results.getItems().stream()
                .collect(Collectors.averagingInt(Person::getAge)));
        return functionResult;
    }


}