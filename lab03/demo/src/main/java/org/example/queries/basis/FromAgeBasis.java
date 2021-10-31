package org.example.queries.basis;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class FromAgeBasis implements Basis {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if(searchParameters.getAgeFrom() > 0) {
            results.setItems(results.getItems().stream()
                    .filter(person -> person.getAge() >= searchParameters.getAgeFrom())
                    .collect(Collectors.toList()));
        }
    }
}