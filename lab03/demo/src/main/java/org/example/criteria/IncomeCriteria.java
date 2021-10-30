package org.example.criteria;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;

public class IncomeCriteria implements Criteria {

    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {

        if(searchParameters.getIncomeTo()!=0){
            List<Person> updatedList = new ArrayList<>();
            results.getItems().stream()
                    .filter(person -> person.getIncome() >= searchParameters.getIncomeFrom())
                    .filter(person -> person.getIncome() <= searchParameters.getIncomeTo())
                    .forEach(updatedList::add);

            results.setItems(updatedList);
        }
    }
}
