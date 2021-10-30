package org.example.queries.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class SurnameCriteria implements Filter {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if (searchParameters.getSurname() != null) {
            results.setItems(results.getItems().stream()
                    .filter(person -> person.getSurname().equalsIgnoreCase(searchParameters.getSurname()))
                    .collect(Collectors.toList())
            );
        }
    }
}
