package org.example.queries.results;

import org.example.model.Person;
import org.example.queries.search.Page;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class PageResult {

    private Results queryResult;

    public PageResult(Results queryResult) {
        this.queryResult = queryResult;
    }

    public Results getCurrentResult(SearchParameters parameters) {

        parameters = setup(parameters);

        int resultPerPage = parameters.getPage().getSize();
        int currentPage = parameters.getPage().getPageNumber();

        queryResult = getPageResult(resultPerPage, currentPage);

        int numberOfPages = queryResult.getItems().size() / resultPerPage;
        queryResult.setPages(numberOfPages);

        return queryResult;
    }

    private Results getPageResult(int queryResultPerPage, int currentPage) {

        int lastResultIndex = queryResultPerPage * currentPage - 1;
        int firstResultIndex = lastResultIndex - queryResultPerPage + 1;

         List<Person> allResults = queryResult.getItems();

         List<Person> currentPageResults = allResults.stream()
                .filter(person -> allResults.indexOf(person) >= firstResultIndex)
                .filter(person -> allResults.indexOf(person) <= lastResultIndex)
                .collect(Collectors.toList());

         queryResult.setItems(currentPageResults);
         queryResult.setCurrentPage(currentPage);

         return queryResult;
    }

    private SearchParameters setup(SearchParameters parameters) {
        Page initialPage = new Page(queryResult.getItems().size(),1);

        if(parameters.getPage() == null
                || parameters.getPage().getSize() > queryResult.getItems().size())
            parameters.setPage(initialPage);

        return parameters;
    }

}

