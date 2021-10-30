package org.example.queries;

import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import org.example.queries.filters.*;
import org.example.queries.calculations.*;

import java.util.List;


public class QueryProcessor {
    List<Filter> filters = List.of(
            new AgeCriteria(),
            new GenderCriteria(),
            new IncomeCriteria(),
            new NameCriteria(),
            new SurnameCriteria(),

            //calculations
            new AverageOfIncomeAndAge(),
            new SumOfIncomeAndAverage()
    );

    public Results GetResults(SearchParameters parameters) {
        Results result = new Results();

        result.setItems(People.Data);

        filters.forEach(filter -> filter.meetCriteria(result, parameters));


        return result;
    }
}
