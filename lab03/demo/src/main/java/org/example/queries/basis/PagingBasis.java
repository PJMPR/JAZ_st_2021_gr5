package org.example.queries.basis;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import org.example.model.Person;

import java.util.ArrayList;

public class PagingBasis implements Basis {
    @Override
    public void meetCriteria(Results result, SearchParameters searchParameters) {
        if (searchParameters.getPage() != null) {

            ArrayList<Person> people = new ArrayList<>();

            int pageSize = searchParameters.getPage().getSize();
            int pageNumber = searchParameters.getPage().getPageNumber();
            int nextPerson = 0;

            result.setCurrentPage(pageNumber);
            result.setPages((result.getItems().size()/pageSize)+1);

            for (int i = 0; i < pageSize; i++) {
                people.add(result.getItems().get(nextPerson++));
                if(nextPerson == result.getItems().size()){
                    break;
                }
            }

            if (pageNumber > 1){
                nextPerson = (pageSize * (pageNumber - 1)) + 1;
            }


            result.setItems(people);
        }
    }
}