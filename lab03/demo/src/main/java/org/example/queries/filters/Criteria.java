package org.example.queries.filters;

import org.example.model.Person;

import java.util.List;

public interface Criteria {
    List<Person> metCriteria(List<Person> persons);
}
