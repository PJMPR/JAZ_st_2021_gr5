package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class FromAgeFilter implements Filter{
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
            results.setItems(results.getItems().stream()
                    .filter(person -> person.getAge() >= searchParameters.getAgeFrom())
                    .collect(Collectors.toList()));
    }
}
