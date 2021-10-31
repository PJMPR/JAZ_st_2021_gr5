package org.example.queries;

import org.example.functions.CombineMethods;
import org.example.model.People;
import org.example.queries.basis.*;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;


public class QueryProcessor {
    List<Basis> filters = List.of(
            new FromAgeBasis(),
            new ToAgeBasis(),
            new FromIncomeBasis(),
            new ToIncomeBasis(),
            new GenderBasis(),
            new NameBasis(),
            new SurnameBasis(),
            new PagingBasis()
    );

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        CombineMethods function = new CombineMethods();

        result.setItems(People.Data);

        filters.forEach(filter -> filter.meetCriteria(result, parameters));

        CombineMethods methods = new CombineMethods();
        function.CombineFunction(parameters, result);

        return result;
    }
}