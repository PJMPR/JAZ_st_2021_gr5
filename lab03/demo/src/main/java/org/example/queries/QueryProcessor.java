package org.example.queries;

import org.example.Functions.CombineMethods;
import org.example.filters.*;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {

    List<Filter> filters = List.of(
            new FromAgeCriteria(),
            new ToAgeCriteria(),
            new FromIncomeCriteria(),
            new ToIncomeCriteria(),
            new GenderCriteria(),
            new NameCriteria(),
            new SurnameCriteria(),
            new PagingCriteria()
    );

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        CombineMethods function = new CombineMethods();

        result.setItems(People.Data);

        filters.forEach(filter -> filter.meetCriteria(result, parameters));

        function.CombineFunction(parameters, result);

        return result;
    }
}
