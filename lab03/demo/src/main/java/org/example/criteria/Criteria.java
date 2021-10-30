package org.example.criteria;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public interface Criteria {
    void meetCriteria(Results results, SearchParameters searchParameters);
}
