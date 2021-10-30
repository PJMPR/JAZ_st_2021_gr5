package org.example;

import org.example.criteria.GenderCriteria;
import org.example.criteria.IncomeCriteria;
import org.example.model.Gender;
import org.example.model.People;
import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.Arrays;
import java.util.List;


public class App {

    public static void main(String[] args){
        IncomeCriteria gc =new IncomeCriteria();
        List<Person> list =  Arrays.asList(People.AnnaBuda,People.ConchitaWurst,People.JanKowalski);
        Results results =new Results();
        SearchParameters searchParameters= new SearchParameters();
//        searchParameters.setSelectedGenders(Arrays.asList(Gender.OTHER,Gender.FEMALE));
        searchParameters.setIncomeFrom(1300);
        searchParameters.setIncomeTo(11000);
        results.setItems(list);
        gc.meetCriteria(results,searchParameters);
        results.getItems().forEach(person -> System.out.println(person.getName()));



    }
}
