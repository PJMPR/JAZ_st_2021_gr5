package org.example.queries;

import org.example.queries.functions.GetFunctionResult;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

public class FuncsProcessor {

    public Results returnResultObject(Results results, SearchParameters parameters) {
        FunctionResult functionResult = new FunctionResult();
        GetFunctionResult getFunctionResult = new GetFunctionResult();

        for (FunctionsParameters functionsParameters : parameters.getFunctions()) {
            functionResult.setFunction(functionsParameters.getFunction());
            functionResult.setFieldName(functionsParameters.getFieldName());

            functionResult = getFunctionResult.getFunctionResultForSumOrAverage(functionResult, results);
            results.getFunctionResults().add(functionResult);
        }

        return results;
    }

}
