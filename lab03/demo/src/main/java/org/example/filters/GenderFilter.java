package org.example.filters;

import org.example.filters.Filter;
import org.example.model.People;
import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class GenderFilter implements Filter {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if(searchParameters.getSelectedGenders().size() > 0){
            results.setItems(results.getItems()
                    .stream()
                    .filter(person -> searchParameters.getSelectedGenders().contains(person.getGender()))
                    .collect(Collectors.toList()));
        }
    }
}
