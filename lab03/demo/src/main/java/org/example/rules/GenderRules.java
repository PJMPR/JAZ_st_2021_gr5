package org.example.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class GenderRules implements Rules{
    @Override
    public void myRules(Results results, SearchParameters searchParameters) {
        if (searchParameters.getSelectedGenders() != null) {
            results.setItems(results.getItems().stream()
                        .filter(person -> searchParameters.getSelectedGenders().contains(person.getGender()))
                        .collect(Collectors.toList()));
        }
    }
}
