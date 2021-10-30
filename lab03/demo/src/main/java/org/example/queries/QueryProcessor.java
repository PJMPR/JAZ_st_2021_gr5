package org.example.queries;

import org.example.criteria.*;
import org.example.functions.AverageFunction;
import org.example.functions.Function;
import org.example.functions.SumFunction;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import javax.naming.Name;
import java.util.Arrays;
import java.util.List;

public class QueryProcessor {
    //wzorzec projektowy Filter, Strategy, Policy
    //https://www.tutorialspoint.com/design_pattern/filter_pattern.htm

    private List<Criteria> criteria = Arrays.asList(
            new AgeCriteria(),
            new GenderCriteria(),
            new IncomeCriteria(),
            new NameCriteria(),
            new SurnameCriteria()
    );

    private List<Function> functions=Arrays.asList(
            new SumFunction(),
            new AverageFunction()
    );


    public Results GetResults(SearchParameters parameters) {
        Results result = new Results();

        result.setItems(People.Data);

        criteria.forEach(criteria->criteria.meetCriteria(result,parameters));
        functions.forEach(function -> function.calculateResult(result,parameters));

        return result;
    }
}
