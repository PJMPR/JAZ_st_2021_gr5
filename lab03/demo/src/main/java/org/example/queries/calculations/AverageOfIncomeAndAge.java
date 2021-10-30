package org.example.queries.calculations;

import org.example.model.Person;
import org.example.queries.filters.Filter;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AverageOfIncomeAndAge implements Filter {

    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        List<FunctionResult> functionResultList = new ArrayList<>();
        FunctionResult functionResult;
        double value;

        List<FunctionsParameters> functionsParameters = searchParameters.getFunctions().stream()
                .filter(fParameters -> fParameters.getFunction().equals(Funcs.AVARAGE)).toList();

        for (FunctionsParameters v : functionsParameters) {
            functionResult = new FunctionResult();
            value = 0.0;

            switch (v.getFieldName()) {
                case "income" -> value = results.getItems().stream().collect(Collectors.averagingDouble(Person::getIncome));
                case "age" -> value = results.getItems().stream().collect(Collectors.averagingInt(Person::getAge));
                default -> throw new IllegalStateException("Unexpected value: " + v.getFieldName());
            }

            functionResult.setFunction(v.getFunction());
            functionResult.setFieldName(v.getFieldName());
            functionResult.setValue(value);
            functionResultList.add(functionResult);
        }

        results.getFunctionResults().addAll(functionResultList);
    }
}
