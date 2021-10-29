package org.example.filters;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SurnameFIlter implements Filter {

    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if(searchParameters.getSurname() != null){
            results.setItems(results.getItems().stream()
                    .filter(person -> person.getSurname().toLowerCase(Locale.ROOT)
                            .equals(searchParameters.getSurname().toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList()));
        }
    }
}
