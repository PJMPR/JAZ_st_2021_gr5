package org.example.rules;

import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;

public class AverageFunc implements Rules{
    @Override
    public void myRules(Results results, SearchParameters searchParameters) {
        List<FunctionResult> fResultList = new ArrayList<>();
        FunctionResult fResult;
        double avg;

        List<FunctionsParameters> functionsParameters =
                searchParameters.getFunctions().stream()
                    .filter(fParameters -> fParameters.getFunction().equals(Funcs.AVARAGE)).toList();

        for (FunctionsParameters func : functionsParameters) {
            fResult = new FunctionResult();
            avg = 0.0;
            if (func.getFieldName().equals("income")) {
                avg = results.getItems().stream()
                        .mapToDouble(Person::getIncome)
                        .average()
                        .orElse(Double.NaN);
            } else if (func.getFieldName().equals("age")) {
                avg = results.getItems().stream()
                        .mapToDouble(Person::getAge)
                        .average()
                        .orElse(Double.NaN);
            }
            fResult.setValue(avg);
            fResult.setFunction(func.getFunction());
            fResult.setFieldName(func.getFieldName());
            results.getFunctionResults().add(fResult);
        }
        results.getFunctionResults().addAll(fResultList);
    }
}
