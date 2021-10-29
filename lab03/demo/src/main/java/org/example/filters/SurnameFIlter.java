package org.example.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class SurnameFIlter implements SearchCriteria{

    @Override
    public List<Person> meetCriteria(List<Person> persons, SearchParameters searchParameters) {
        return persons.stream()
                .filter(person -> person.getSurname().equals(searchParameters.getSurname()))
                .collect(Collectors.toList());
    }
}
