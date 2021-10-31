
package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class PagingCriteria implements Filter {
    @Override
    public void meetCriteria(Results result, SearchParameters searchParameters) {
        if (searchParameters.getPage() != null) {

            int numberOfPages = 0;
            int toSkip = (result.getCurrentPage() - 1) * searchParameters.getPage().getSize();

            if (result.getItems().size() < searchParameters.getPage().getSize()) {
                if (result.getItems().size() % searchParameters.getPage().getSize() != 0) {
                    numberOfPages = result.getItems().size() / searchParameters.getPage().getSize() + 1;
                } else {
                    numberOfPages = result.getItems().size() / searchParameters.getPage().getSize();
                }
            }

            result.setPages(numberOfPages);
            result.setCurrentPage(searchParameters.getPage().getPageNumber());
            try {
                result.setItems(result.getItems().stream()
                        .skip(toSkip)
                        .limit(searchParameters.getPage().getSize())
                        .collect(Collectors.toList()));
            }
            catch (Exception e){
                System.out.println("Something went wrong");
            }
        }
    }
}