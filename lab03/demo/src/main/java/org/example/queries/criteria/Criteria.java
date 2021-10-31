package org.example.queries.criteria;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;
import java.util.List;

public interface Criteria {

    public List<Person> meetCriteria(List<Person> persons, SearchParameters searchParameters);

    // Check wheather Criteria can be procceded
    public boolean isSet(SearchParameters searchParameters);
}
