package org.example.queries;

import org.example.model.People;
import org.example.queries.basis.*;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import org.example.queries.basis.Basis;

import java.util.List;


public class QueryProcessor {
    List<Basis> filters = List.of(
            new AgeBasis(),
            new GenderBasis(),
            new NameBasis(),
            new SurnameBasis()

    );

    public Results GetResults(SearchParameters parameters) {
        Results result = new Results();

        result.setItems(People.Data);

        filters.forEach(filter -> filter.meetCriteria(result, parameters));


        return result;
    }
}