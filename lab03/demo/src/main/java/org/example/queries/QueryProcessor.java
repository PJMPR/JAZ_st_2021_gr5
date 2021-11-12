package org.example.queries;

import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import org.example.queries.search.filters.Filter;
import org.example.queries.search.filters.SimpleFilter;

import java.util.List;

public class QueryProcessor {

    List<Filter> filters = List.of(
            new SimpleFilter(params->params.getAgeTo()>0, (person, params)->person.getAge()<params.getAgeTo()),
            new SimpleFilter(params->params.getName()!=null&&!params.getName().equals(""),
                    (person, params)->person.getName().equalsIgnoreCase(params.getName()))
    )

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();

        result.setItems(People.Data);

        return result;
    }
}
