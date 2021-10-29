package org.example.filters;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public interface Filter {
     void meetCriteria (Results results, SearchParameters searchParameters);

}
