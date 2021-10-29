package org.example.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;

public interface SearchCriteria {
    public List<Person> meetCriteria (List<Person> persons , SearchParameters searchParameters);
}
