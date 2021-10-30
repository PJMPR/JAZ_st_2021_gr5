package org.example.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;


public interface Rules {
    void myRules(Results results, SearchParameters searchParameters);
}
