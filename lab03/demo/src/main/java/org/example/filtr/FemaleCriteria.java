package org.example.filtr;

import org.example.model.Gender;
import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;

public class FemaleCriteria extends GenderCriteria{
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();

        persons.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .forEach(malePersons::add);

        return malePersons;
    }
}
