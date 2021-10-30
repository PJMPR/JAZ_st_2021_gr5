package org.example.functions;

import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;

public class AverageFunction implements Function{
    @Override
    public void calculateResult(Results results, SearchParameters searchParameters) {
        List<FunctionsParameters> functionsParametersList = new ArrayList<>();
        searchParameters.getFunctions().stream()
                .filter(functionsParameters -> functionsParameters.getFunction().equals(Funcs.SUM))
                .forEach(functionsParametersList::add);

        for (FunctionsParameters f:functionsParametersList) {
            FunctionResult functionResult=new FunctionResult();
            double average=0;
            if(f.getFieldName().equals("age")){
                average =results.getItems().stream()
                        .mapToDouble(Person::getAge)
                        .average()
                        .orElse(Double.NaN);
            }else if(f.getFieldName().equals("income")){
                average =results.getItems().stream()
                        .mapToDouble(Person::getIncome)
                        .average()
                        .orElse(Double.NaN);
            }
            functionResult.setFieldName(f.getFieldName());
            functionResult.setFunction(f.getFunction());
            functionResult.setValue(average);

            results.getFunctionResults().add(functionResult);
        }

    }
}
