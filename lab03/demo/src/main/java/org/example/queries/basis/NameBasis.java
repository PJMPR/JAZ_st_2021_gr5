package org.example.queries.basis;

import org.example.queries.basis.Basis;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class NameBasis implements Basis {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if (searchParameters.getName() != null) {
            results.setItems(
                    results.getItems().stream()
                            .filter(person -> searchParameters.getName().equalsIgnoreCase(person.getName()))
                            .collect(Collectors.toList())
            );
        }
    }
}