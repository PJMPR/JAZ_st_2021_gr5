package org.example.criteria;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;

public class NameCriteria implements Criteria{
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if(searchParameters.getName()!=null){
            List<Person> updatedList = new ArrayList<>();
            results.getItems().stream()
                    .filter(person -> searchParameters.getName().equals(person.getName()))
                    .forEach(updatedList::add);

            results.setItems(updatedList);
        }
    }
}
