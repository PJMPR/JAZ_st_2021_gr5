package org.example.queries.filters;

import org.example.model.Gender;
import org.example.model.Person;

import java.util.List;

public class CriteriaMale implements Criteria{

    @Override
    public List<Person> metCriteria(List<Person> persons) {
        persons.stream()
                .filter(person -> person.getGender() == Gender.MALE)
                .forEach();
    }
}
