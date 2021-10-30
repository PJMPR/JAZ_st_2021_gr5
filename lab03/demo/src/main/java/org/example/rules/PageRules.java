package org.example.rules;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;


public class PageRules implements Rules{

    public int howManyPages(Results result, SearchParameters searchParameters) {
        int elements = result.getItems().size();
        int maxElems = searchParameters.getPage().getSize();
        if (!(maxElems >= elements)) {
            if (elements % maxElems != 0) { return elements / maxElems + 1;
            } else { return elements / maxElems; }
        } return 1;
    }

    @Override
    public void myRules(Results results, SearchParameters searchParameters) {
        if (searchParameters.getPage() != null) {
            results.setPages(howManyPages(results, searchParameters));
            results.setCurrentPage(searchParameters.getPage().getPageNumber());
            results.setItems(results.getItems().stream()
                    .skip(((int)results.getCurrentPage() - 1) * ((int)searchParameters.getPage().getSize()))
                    .limit(searchParameters.getPage().getSize())
                    .collect(Collectors.toList()));
        }
    }
}

