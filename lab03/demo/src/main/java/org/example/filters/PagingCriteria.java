
package org.example.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class PagingCriteria implements Filter {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if (searchParameters.getPage() != null) {

        }
    }
}