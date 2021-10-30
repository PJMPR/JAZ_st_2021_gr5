package org.example.queries;

import org.example.filters.*;
import org.example.functions.ApplyFunctions;
import org.example.model.People;
import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {

    List<Filter> filters = List.of(
            new FromAgeFilter(),
            new ToAgeFilter(),
            new GenderFilter(),
            new FromIncomeFilter(),
            new ToIncomeFilter(),
            new SurnameFIlter(),
            new NameFilter(),
            new PageFilter()
    );

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();

        result.setItems(People.Data);

        filters.forEach(filter -> filter.meetCriteria(result, parameters));

        ApplyFunctions functions = new ApplyFunctions();
        functions.applyFunctions(result,parameters);

        return result;
    }
}
