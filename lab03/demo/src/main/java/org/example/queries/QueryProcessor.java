package org.example.queries;

import org.example.model.People;
import org.example.model.Person;
import org.example.queries.process.*;
import org.example.queries.results.Results;
import org.example.queries.search.PageManager;
import org.example.queries.search.SearchParameters;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class QueryProcessor {

    public List<Criteria> criteriaList = List.of(
            new AgeCriteria(),
            new GenderCriteria(),
            new IncomeCriteria(),
            new NameCriteria(),
            new SurnameCriteria());


    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        PageManager pageManager = new PageManager();

        AtomicReference<List<Person>> searchResult = new AtomicReference<>(People.Data);
        criteriaList.forEach(Criteria -> searchResult.set(Criteria.meetCriteria(searchResult.get(), parameters)));

        searchResult.set(pageManager.getPageResult(searchResult.get(), parameters));

        if (parameters.getPage() != null) {
            if (parameters.getPage().getSize() > searchResult.get().size()) {
                result.setCurrentPage(1);
                result.setPages(1);
            }
        }

        result.setItems(searchResult.get());
        return result;
    }

}
