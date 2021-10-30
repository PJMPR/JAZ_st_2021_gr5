package org.example.criteria;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;


public class GenderCriteria implements Criteria {

    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if(searchParameters.getSelectedGenders().size()>0){
            List<Person> updatedList = new ArrayList<>();
            results.getItems().stream()
                    .filter(person -> searchParameters.getSelectedGenders().contains(person.getGender()))
                    .forEach(updatedList::add);

            results.setItems(updatedList);
        }
    }
}
