package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import java.util.stream.Collectors;

public class NameCriteria implements Filter {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if(searchParameters.getName() != null){
            results.setItems(results.getItems().stream()
                    .filter(person -> person.getName().equalsIgnoreCase(searchParameters.getName()))
                    .collect(Collectors.toList()));
        }
    }
}