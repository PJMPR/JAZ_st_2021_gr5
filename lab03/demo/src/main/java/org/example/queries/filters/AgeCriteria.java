package org.example.queries.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class AgeCriteria implements Filter {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if (searchParameters.getAgeFrom() != 0) {
            results.setItems(
                    results.getItems().stream()
                            .filter(person -> person.getAge() >= searchParameters.getAgeFrom())
                            .collect(Collectors.toList()));
        }
        if (searchParameters.getAgeTo() != 0) {
            results.setItems(
                    results.getItems().stream()
                            .filter(person -> person.getAge() <= searchParameters.getAgeTo())
                            .collect(Collectors.toList()));
        }
    }
}
