package org.example.queries;

import org.example.filter.*;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {

    List<Filter> filters = List.of(
            new AgeFilter(),
            new GenderFilter(),
            new IncomeFilter(),
            new NameFilter(),
            new SurnameFilter()
    );

    public Results GetResults(SearchParameters parameters) {
        Results results = new Results();
        results.setItems(People.Data);

        filters.forEach(filter -> filter.Filter(results, parameters));

        System.out.println(results);

        return results;



    }

}