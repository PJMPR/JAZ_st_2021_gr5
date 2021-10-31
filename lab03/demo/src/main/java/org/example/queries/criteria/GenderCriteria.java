package org.example.queries.criteria;

import org.example.model.Gender;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;
import java.util.List;
import java.util.stream.Collectors;

public class GenderCriteria implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons, SearchParameters searchParameters) {
        if(isSet(searchParameters)) {
            List<Gender> expectedGender = searchParameters.getSelectedGenders();
            return persons.stream()
                    .filter(person -> expectedGender.contains(person.getGender()))
                    .collect(Collectors.toList());
        }
        else {
            return persons;
        }
    }

    @Override
    public boolean isSet(SearchParameters searchParameters) {
        return !searchParameters.getSelectedGenders().isEmpty();
    }
}
