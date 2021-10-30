package org.example.queries;

import org.example.model.People;
import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QueryProcessor {
    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        List<Person> peopleList = People.Data.stream()
                .filter(person -> parameters.getAgeFrom() == 0 || person.getAge()>=parameters.getAgeFrom())
                .filter(person -> parameters.getAgeTo() == 0 || person.getAge()<=parameters.getAgeTo())
                .filter(person -> parameters.getIncomeFrom() == 0 || person.getIncome()>=parameters.getIncomeFrom())
                .filter(person -> parameters.getIncomeTo() == 0 || person.getIncome()<=parameters.getIncomeTo())
                .filter(person -> parameters.getSelectedGenders().isEmpty() || parameters.getSelectedGenders().contains(person.getGender()))
                .filter(person -> parameters.getName() == null || person.getName().equals(parameters.getName().toLowerCase()))
                .collect(Collectors.toList());

        result.setItems(peopleList);
        result.setFunctionResults(getFunctionResults(parameters, peopleList));

        if (parameters.getPage() != null){
            result.setPages(parameters.getPage().getPageNumber());
            result.setCurrentPage((int)Math.ceil((double)peopleList.size()/parameters.getPage().getSize()));
        }

        return result;
    }

    public double getAvarage(List<Person> peopleList, String fieldName){
        if (fieldName.equals("age")){
            return peopleList.stream()
                    .collect(Collectors.averagingDouble(Person::getAge));
        }
        else if (fieldName.equals("income")){
            return peopleList.stream()
                    .collect(Collectors.averagingDouble(Person::getIncome));
        }
        return 0;
    }

    public double getSum(List<Person> peopleList, String fieldName){
        if (fieldName.equals("age")){
            return peopleList.stream().mapToDouble(Person::getAge).sum();
        } else if (fieldName.equals("income")){
            return peopleList.stream().mapToDouble(Person::getIncome).sum();
        }
        return 0;
    }

    public List<FunctionResult> getFunctionResults(SearchParameters parameters, List<Person> peopleList){
        List<FunctionResult> results = new ArrayList<>();
        for(FunctionsParameters func : parameters.getFunctions()){
            double value = 0;
            if(func.getFunction() == Funcs.AVARAGE){
                value = getAvarage(peopleList, func.getFieldName());
            } else if(func.getFunction() == Funcs.SUM){
                value = getSum(peopleList, func.getFieldName());
            }
            FunctionResult res = new FunctionResult();
            res.setFunction(func.getFunction());
            res.setFieldName(func.getFieldName());
            res.setValue(value);
            results.add(res);
        }
        return results;
    }
}
