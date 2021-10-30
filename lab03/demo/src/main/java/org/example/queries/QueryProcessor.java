package org.example.queries;

import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import org.example.rules.*;
import org.example.rules.Rules;

import java.util.List;

public class QueryProcessor {
    public List<Rules> rules = List.of(
            new AgeRules(),
            new AgeRules2(),
            new NameRules(),
            new SurnameRules(),
            new GenderRules(),
            new IncomeRules(),
            new IncomeRules2(),
            new PageRules(),
            new AverageFunc(),
            new SumFunc()
    );

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        result.setItems(People.Data);

        rules.forEach(rule -> rule.myRules(result,parameters));

        return result;
    }
}
