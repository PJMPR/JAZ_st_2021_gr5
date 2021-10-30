package org.example.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class AgeRules implements Rules{
    @Override
    public void myRules(Results results, SearchParameters searchParams) {
        results.setItems(results.getItems().stream()
                    .filter(person -> person.getAge() >= searchParams.getAgeFrom())
                    .collect(Collectors.toList()));
        }
    }
