package org.example.queries;

import org.example.model.People;
import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();

        result.setItems(People.Data);

        List<Person> people = result.getItems();



        return result;
    }
}
