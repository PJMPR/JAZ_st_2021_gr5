package org.example.rules;

import org.example.queries.results.Results;
import org.example.rules.Rules;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class NameRules implements Rules {
    @Override
    public void myRules(Results results, SearchParameters searchParameters) {
        if(searchParameters.getName() != null){
            results.setItems(results.getItems().stream()
                    .filter(person -> person.getName().equalsIgnoreCase(searchParameters.getName()))
                    .collect(Collectors.toList()));
        }
        }
    }
