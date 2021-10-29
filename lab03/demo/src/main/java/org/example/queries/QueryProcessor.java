package org.example.queries;

import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();

        result.setItems(People.Data);

        return result;
    }
}
