package org.example;

import org.example.criteria.GenderCriteria;
import org.example.criteria.IncomeCriteria;
import org.example.model.Gender;
import org.example.model.People;
import org.example.model.Person;
import org.example.queries.QueryProcessor;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.Arrays;
import java.util.List;


public class App {

    public static void main(String[] args){
        QueryProcessor queryProcessor=new QueryProcessor();
        SearchParameters searchParameters= new SearchParameters();
        searchParameters.setIncomeFrom(1300);
        searchParameters.setIncomeTo(11000);
        Results results  = queryProcessor.GetResults(searchParameters);
        results.getItems().forEach(person -> System.out.println(person.getName() +" " + person.getSurname()));



    }
}
