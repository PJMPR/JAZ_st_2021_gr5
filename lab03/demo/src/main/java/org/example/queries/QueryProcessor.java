package org.example.queries;

import org.example.criteria.*;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import javax.naming.Name;
import java.util.Arrays;
import java.util.List;

public class QueryProcessor {
    //wzorzec projektowy Filter, Strategy, Policy
    //https://www.tutorialspoint.com/design_pattern/filter_pattern.htm

    public List<Criteria> criteria = Arrays.asList(
            new AgeCriteria(),
            new GenderCriteria(),
            new IncomeCriteria(),
            new NameCriteria(),
            new SurnameCriteria()
    );

    public Results GetResults(SearchParameters parameters) {
        Results result = new Results();

        result.setItems(People.Data);

        criteria
                .forEach(criteria->criteria.meetCriteria(result,parameters));

        return result;
    }
}
