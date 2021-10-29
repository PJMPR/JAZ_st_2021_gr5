package org.example.queries;

import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class QueryProcessor {
    //TODO: wzorzec filter, strategy/policy (design pattern)
    //https://www.tutorialspoint.com/design_pattern/filter_pattern.htm

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();

        result.setItems(People.Data);



        return result;
    }
}
