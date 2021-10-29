package org.example.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class NameFilter implements SearchCriteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons, SearchParameters searchParameters) {
        return persons.stream()
                .filter(person -> person.getName().equals(searchParameters.getName()))
                .collect(Collectors.toList());
    }
}
