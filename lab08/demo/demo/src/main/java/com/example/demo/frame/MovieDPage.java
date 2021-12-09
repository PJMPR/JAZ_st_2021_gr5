package com.example.demo.frame;

import com.example.demo.contract.MovieDto;

import java.util.List;

public class MovieDPage {
    int page;
    List<MovieDto> results;
    int total_pages;
    int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieDto> getResults() {
        return results;
    }

    public void setResults(List<MovieDto> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
