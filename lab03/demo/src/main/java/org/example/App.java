package org.example;

import org.example.model.Gender;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.Page;
import org.example.queries.search.SearchParameters;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args){
        SearchParameters params =new SearchParameters();
        params.setName("Jan");
       // params.setAgeFrom(20);
        params.setAgeTo(40);
        params.setIncomeFrom(2000);
        params.setPage(new Page(9,1));
       // params.getSelectedGenders().add(Gender.FEMALE);
       // params.getSelectedGenders().add(Gender.OTHER);
        params.getFunctions().add(new FunctionsParameters("age", Funcs.AVARAGE));
        params.getFunctions().add(new FunctionsParameters("income", Funcs.SUM));
        params.getFunctions().add(new FunctionsParameters("income", Funcs.AVARAGE));

        // dla stringa sprawdzac czy null
        //dla inta moze byc przyjmowane 0
        //dla genders jest pusta tablica


    }
}
