package org.example.queries.search.filters;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public abstract class FilterBase implements Filter{

    SearchParameters searchParameters;

    public void setParameters(SearchParameters searchParameters){
        this.searchParameters=searchParameters;
    }

    @Override
    public void filter(Results results ) {
        if(checkParameters()){
            results.setItems(results.getItems()
                    .stream()
                    .filter(person->checkPerson(person))
                    .collect(Collectors.toList()));
        }
    }

    protected abstract boolean checkPerson(Person person);

    protected abstract boolean checkParameters();
}
