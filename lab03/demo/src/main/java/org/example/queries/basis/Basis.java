package org.example.queries.basis;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;


public interface Basis {
    void meetCriteria(Results results, SearchParameters searchParameters);
}