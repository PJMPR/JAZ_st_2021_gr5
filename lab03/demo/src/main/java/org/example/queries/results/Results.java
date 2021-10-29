package org.example.queries.results;

import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;

public class Results {

    private List<Person> items = new ArrayList<>();
    private List<FunctionResult> functionResults = new ArrayList<>();
    private int pages;
    private int currentPage;

    public List<Person> getItems() {
        return items;
    }

    public void setItems(List<Person> items) {
        this.items = items;
    }

    public List<FunctionResult> getFunctionResults() {
        return functionResults;
    }

    public void setFunctionResults(List<FunctionResult> functionResults) {
        this.functionResults = functionResults;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
