package org.example.queries.criteria;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;
import java.util.List;
import java.util.stream.Collectors;

public class IncomeCriteria implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons, SearchParameters searchParameters) {

        if(searchParameters.getIncomeTo() != 0) {
            persons = persons.stream()
                    .filter(person -> person.getIncome() <= searchParameters.getIncomeTo())
                    .collect(Collectors.toList());
        }

        if(searchParameters.getIncomeFrom() != 0) {
            persons = persons.stream()
                    .filter(person -> person.getIncome() >= searchParameters.getIncomeFrom())
                    .collect(Collectors.toList());
        }

        return persons;

    }

    @Override
    public boolean isSet(SearchParameters searchParameters) {
        return true;
    }

}
