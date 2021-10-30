package org.example;
import org.example.queries.QueryProcessor;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;




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
