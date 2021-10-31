
package org.example.filters;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PagingCriteria implements Filter {
    @Override
    public void meetCriteria(Results result, SearchParameters searchParameters) {
        if (searchParameters.getPage() != null) {
            int pageSize = searchParameters.getPage().getSize();
            int pageNumber = searchParameters.getPage().getPageNumber();
            int nextPerson = 0;
            ArrayList<Person> people = new ArrayList<>();

            result.setCurrentPage(pageNumber);
            result.setPages((result.getItems().size()/pageSize)+1);

            if (pageNumber > 1){
                nextPerson = (pageSize * (pageNumber - 1)) + 1;
            }

            for (int i = 0; i < pageSize; i++) {
                people.add(result.getItems().get(nextPerson++));
                if(nextPerson == result.getItems().size()){
                    break;
                }
            }
            result.setItems(people);
        }
    }
}