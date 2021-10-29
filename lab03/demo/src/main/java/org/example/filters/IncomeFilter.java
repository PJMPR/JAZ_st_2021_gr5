package org.example.filters;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class IncomeFilter implements Filter {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
                results.setItems(results.getItems().stream()
                .filter(person -> person.getIncome() >= searchParameters.getIncomeFrom())
                .filter(person -> person.getIncome() <= searchParameters.getIncomeTo())
                .collect(Collectors.toList()));
    }
}
