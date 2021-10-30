package org.example.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class SurnameRules implements Rules {
    @Override
    public void myRules(Results results, SearchParameters searchParameters) {
        if (searchParameters.getSurname() != null) {
            results.setItems(results.getItems().stream()
                    .filter(person -> searchParameters.getSurname().equalsIgnoreCase(person.getSurname()))
                    .collect(Collectors.toList())
            );
        }
    }
}
