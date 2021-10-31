package org.example.queries.criteria;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;
import java.util.List;
import java.util.stream.Collectors;

public class NameCriteria implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons, SearchParameters searchParameters) {
        if(isSet(searchParameters)) {

            String expectedName = searchParameters.getName().toLowerCase();
            return persons.stream()
                    .filter(person -> expectedName.equals(person.getName().toLowerCase()))
                    .collect(Collectors.toList());
        }
        else {
            return persons;
        }
    }

    @Override
    public boolean isSet(SearchParameters searchParameters) {
        return searchParameters.getName() != null;
    }

}
