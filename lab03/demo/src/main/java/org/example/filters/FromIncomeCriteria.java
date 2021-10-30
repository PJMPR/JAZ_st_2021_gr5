package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class FromIncomeCriteria implements Filter{
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if(searchParameters.getIncomeFrom() > 0) {
            results.setItems(results.getItems().stream()
                    .filter(person -> person.getIncome() >= searchParameters.getIncomeFrom())
                    .collect(Collectors.toList()));
        }
    }
}