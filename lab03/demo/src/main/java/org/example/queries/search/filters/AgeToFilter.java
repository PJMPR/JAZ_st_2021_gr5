package org.example.queries.search.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

public class AgeToFilter extends FilterBase{
    @Override
    protected boolean checkPerson(Person person) {
        return person.getAge()<searchParameters.getAgeTo();
    }

    @Override
    protected boolean checkParameters() {
        return searchParameters.getAgeTo()>0;
    }
}
