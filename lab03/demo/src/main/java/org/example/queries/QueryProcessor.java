package org.example.queries;

import org.example.model.Gender;
import org.example.model.People;
import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.Page;
import org.example.queries.search.SearchParameters;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        List<Person> people = filter(new People(), parameters);

        result.setItems(people);

        return setPages(result, parameters);
    }

    private Results setPages(Results result, SearchParameters parameters) {
        if(parameters.getPage() != null){
            int elements = result.getItems().size();
            int pNumber = parameters.getPage().getPageNumber();
            int pSize = parameters.getPage().getSize();

            int max = pSize * pNumber;
            int min = max - pSize;
            for (int i = 0; i < elements; i++) {
                if(i < min || i > max){
                    result.getItems().remove(i);
                }
            }
            result.setPages(elements/pSize + 1);
            result.setCurrentPage(pNumber);
        }
        return result;
    }

    private List<Person> filter(People people, SearchParameters parameters) {
        List<Person> list = people.Data;
        if(parameters.getName() != null){
            list = list.stream().filter(person -> person.getName().equals(parameters.getName().toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        }
        if(parameters.getSurname() != null){
            list = list.stream().filter(person -> person.getSurname().equals(parameters.getSurname().toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        }
        if(parameters.getAgeFrom() != 0){
            list = list.stream().filter(person -> person.getAge()>=parameters.getAgeFrom()).collect(Collectors.toList());
        }
        if(parameters.getAgeTo() != 0){
            list = list.stream().filter(person -> person.getAge()<=parameters.getAgeTo()).collect(Collectors.toList());
        }
        if(parameters.getIncomeFrom() != 0){
            list = list.stream().filter(person -> person.getIncome()>=parameters.getIncomeFrom()).collect(Collectors.toList());
        }
        if(parameters.getIncomeTo() != 0){
            list = list.stream().filter(person -> person.getIncome()<=parameters.getIncomeTo()).collect(Collectors.toList());
        }
        List<Gender> selectedGenders = parameters.getSelectedGenders();
        if(selectedGenders.size() == 2){
            list = list.stream().filter(person -> person.getGender()==selectedGenders.get(0) || person.getGender()==selectedGenders.get(1))
                    .collect(Collectors.toList());
        }else if(selectedGenders.size() == 1){
            list = list.stream().filter(person -> person.getGender()==selectedGenders.get(0)).collect(Collectors.toList());
        }
        return list;
    }
}