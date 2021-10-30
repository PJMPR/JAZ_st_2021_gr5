package org.example.queries.search;

import org.example.model.Person;
import java.util.List;
import java.util.stream.Collectors;

public class PageManager {

    public List<Person> getPageResult(List<Person> result, SearchParameters parameters) {

        Page page = parameters.getPage();

        if (page != null) {

            int lastResultIndex = page.getSize() * page.getPageNumber();
            int firstResultIndex = lastResultIndex - page.getSize();

            return result.stream()
                    .filter(person -> result.indexOf(person) >= firstResultIndex
                            && result.indexOf(person) < lastResultIndex)
                    .collect(Collectors.toList());
        }

        return result;
    }
}
