package org.example.functions;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;


public class CombineMethods {
    SumAndAverage calculations = new SumAndAverage();
    public void CombineFunction(SearchParameters searchParameters, Results results) {
        List<FunctionResult> functionResults = new ArrayList<>();
        for (FunctionsParameters f : searchParameters.getFunctions()) {
            if (f.getFunction() == Funcs.AVARAGE) {
                switch (f.getFieldName()) {
                    case "age" ->
                            functionResults.add(calculations.averageAge(results));
                    case "income" ->
                            functionResults.add(calculations.averageIncome(results));
                }
            }
            else if (f.getFunction() == Funcs.SUM) {
                switch (f.getFieldName()){
                    case "age" ->
                            functionResults.add(calculations.sumAge(results));
                    case "income" ->
                            functionResults.add(calculations.sumIncome(results));
                }
            }
        }
        results.setFunctionResults(functionResults);
    }
}
