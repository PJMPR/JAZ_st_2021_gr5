package org.example.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class AgeFilter implements SearchCriteria{
    @Override
    public List<Person> meetCriteria(List<Person> persons, SearchParameters searchParameters) {
        return persons.stream()
                .filter(person -> person.getAge() >= searchParameters.getAgeFrom())
                .filter(person -> person.getAge() <= searchParameters.getAgeTo())
                .collect(Collectors.toList());
    }
}
