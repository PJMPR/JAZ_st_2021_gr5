package org.example.queries;

import org.example.model.Gender;
import org.example.model.People;
import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.*;
import java.util.stream.Collectors;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();

        setPeople(result, parameters);
        setPages(result, parameters);
        setPeopleOnPage(result, parameters);
        setFunctions(result, parameters);

        return result;
    }

    private void setFunctions(Results result, SearchParameters parameters) {
        ArrayList<FunctionResult> functionResults = new ArrayList<>();
        for (FunctionsParameters functionsParameters:
                parameters.getFunctions()) {
            FunctionResult functionResult = new FunctionResult();
            functionResult.setFunction(functionsParameters.getFunction());
            functionResult.setFieldName(functionsParameters.getFieldName());
            functionResult.setValue(functionValue(result, functionsParameters));
            functionResults.add(functionResult);
        }
        result.setFunctionResults(functionResults);
    }

    private double functionValue(Results result, FunctionsParameters functionsParameters) {
        if(functionsParameters.getFunction() == Funcs.AVARAGE){
            return averageValue(result, functionsParameters.getFieldName());
        }else if(functionsParameters.getFunction() == Funcs.SUM){
            return sumValue(result, functionsParameters.getFieldName());
        }else{
            return 0;
        }
    }

    private double averageValue(Results result, String fieldName) {
        return sumValue(result, fieldName)/result.getItems().size();
    }

    private double sumValue(Results result, String fieldName) {
        if (fieldName.toLowerCase(Locale.ROOT).equals("age")){
            return result.getItems().stream().mapToDouble(Person::getAge).sum();
        }else if (fieldName.toLowerCase(Locale.ROOT).equals("income")){
            return result.getItems().stream().mapToDouble(Person::getIncome).sum();
        }else {
            return 0;
        }
    }


    private void setPeopleOnPage(Results result, SearchParameters parameters) {
        if(parameters.getPage() != null) {
            int pageSize = parameters.getPage().getSize();
            int pageNumber = result.getCurrentPage();
            ArrayList<Person> people = new ArrayList<>();

            int nextPerson = 0;
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

    private void setPages(Results result, SearchParameters parameters) {
        if(parameters.getPage() != null){
            int pageSize = parameters.getPage().getSize();
            int pageNumber = parameters.getPage().getPageNumber();
            result.setCurrentPage(pageNumber);
            result.setPages((result.getItems().size()/pageSize)+1);
        }
    }

    private void setPeople(Results result, SearchParameters parameters) {
        List<Person> list = People.Data;

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
        result.setItems(list);
    }
}