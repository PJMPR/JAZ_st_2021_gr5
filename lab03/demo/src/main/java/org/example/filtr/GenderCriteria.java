package org.example.filtr;

import org.example.model.Person;

import java.util.List;

public abstract class GenderCriteria implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return null;
    }
}
