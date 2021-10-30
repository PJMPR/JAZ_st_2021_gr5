package org.example.criteria;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class AgeCriteria implements Criteria{

    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {

        List<Person> updatedList=results.getItems().stream()
                .filter(person -> searchParameters.getAgeFrom()<=person.getAge())
                .filter(person -> searchParameters.getAgeTo()>=person.getAge())
                .collect(Collectors.toList());

        results.setItems(updatedList);
    }
}
