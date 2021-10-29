package org.example.queries;

import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class QueryProcessor {
    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        People.Data.stream()
                .filter(age -> age.getAge()>=20 && age.getAge()<=40)
                .filter(income -> income.getIncome()>=2000)
                //.filter(page -> page.getSize)
        result.setItems(People.Data);

        return result;
    }
}
