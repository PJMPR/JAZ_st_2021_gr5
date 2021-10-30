package org.example.functions;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public interface Function {
    void calculateResult(Results results, SearchParameters searchParameters);
}
