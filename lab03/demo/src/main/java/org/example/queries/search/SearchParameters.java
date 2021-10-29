package org.example.queries.search;

import org.example.model.Gender;

import java.util.ArrayList;
import java.util.List;

public class SearchParameters {
    private String name;
    private String surname;
    private int ageFrom;
    private int ageTo;
    private double incomeFrom;
    private double incomeTo;
    private List<Gender> selectedGenders = new ArrayList<Gender>();
    private List<FunctionsParameters> functions = new ArrayList<FunctionsParameters>();
    private Page page;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(int ageFrom) {
        this.ageFrom = ageFrom;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }

    public double getIncomeFrom() {
        return incomeFrom;
    }

    public void setIncomeFrom(double incomeFrom) {
        this.incomeFrom = incomeFrom;
    }

    public double getIncomeTo() {
        return incomeTo;
    }

    public void setIncomeTo(double incomeTo) {
        this.incomeTo = incomeTo;
    }

    public List<Gender> getSelectedGenders() {
        return selectedGenders;
    }

    public void setSelectedGenders(List<Gender> selectedGenders) {
        this.selectedGenders = selectedGenders;
    }

    public List<FunctionsParameters> getFunctions() {
        return functions;
    }

    public void setFunctions(List<FunctionsParameters> functions) {
        this.functions = functions;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
