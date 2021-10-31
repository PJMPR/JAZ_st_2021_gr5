package org.example.queries.basis;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class ToAgeBasis implements Basis{
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if(searchParameters.getAgeTo() > 0){
            results.setItems(results.getItems().stream()
                    .filter(person -> person.getAge() <= searchParameters.getAgeTo())
                    .collect(Collectors.toList()));
        }
    }
}