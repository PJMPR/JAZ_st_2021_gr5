package org.example.queries.search.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

@FunctionalInterface
public interface FilterPredicate {
    boolean check(Person person, SearchParameters searchParameters);
}
