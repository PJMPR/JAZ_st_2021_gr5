package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public interface Filter {
    void meetCriteria(Results results, SearchParameters searchParameters);
}
