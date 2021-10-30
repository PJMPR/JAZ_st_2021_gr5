package org.example.queries.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class GenderCriteria implements Filter {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if (!searchParameters.getSelectedGenders().isEmpty()) {
            results.setItems(
                    results.getItems().stream()
                            .filter(person -> searchParameters.getSelectedGenders().contains(person.getGender()))
                            .collect(Collectors.toList())
            );
        }
    }
}
