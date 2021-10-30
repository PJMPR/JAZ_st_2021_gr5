package org.example.functions;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
// na pewno mozna te klase jakos przeksztalcic,
// ale niestety nie mialem pomyslu (a raczej wystarczajaco duzo czasu) jak :/

public class ApplyFunctions { public void applyFunctions(Results results, SearchParameters searchParameters) {

        List<FunctionResult> functionResults = new ArrayList<>();
        for (FunctionsParameters f : searchParameters.getFunctions()) {
            if (f.getFunction() == Funcs.SUM) {
                if (Objects.equals(f.getFieldName(), "age")) {
                    functionResults.add(sumAge(results, searchParameters));
                }
                if (Objects.equals(f.getFieldName(), "income")) {
                    functionResults.add(sumIncome(results, searchParameters));
                }

            } else if (f.getFunction() == Funcs.AVARAGE) {
                if (Objects.equals(f.getFieldName(), "age")) {
                    functionResults.add(averageAge(results, searchParameters));
                }
                if (Objects.equals(f.getFieldName(), "income")) {
                    functionResults.add(averageIncome(results, searchParameters));
                }
            }
        }
        results.setFunctionResults(functionResults);
    }

    private FunctionResult averageAge(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("age");
        functionResult.setValue(results.getItems()
                .stream()
                .collect(Collectors.averagingInt(person -> person.getAge())));
        return functionResult;
    }

    private FunctionResult averageIncome(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("income");
        functionResult.setValue(results.getItems()
                .stream()
                .collect(Collectors.averagingDouble(person -> person.getIncome())));
        return functionResult;
    }

    public FunctionResult sumAge(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("income");
        functionResult.setValue(
        results.getItems()
                .stream()
                .mapToInt(person -> person.getAge()).sum());
        return functionResult;
    }

    private FunctionResult sumIncome(Results results, SearchParameters searchParameters) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("income");
        functionResult.setValue(
        results.getItems().stream().mapToDouble(person -> person.getIncome()).sum());
        return functionResult;
    }

}
