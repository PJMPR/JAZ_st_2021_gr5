package com.example.demo.charts;

import org.jfree.data.category.DefaultCategoryDataset;

import javax.servlet.http.HttpServletResponse;

public abstract class Chart {
    HttpServletResponse response;
    DefaultCategoryDataset categoryDataset;

    public Chart(HttpServletResponse response, DefaultCategoryDataset categoryDataset) {
        this.response = response;
        this.categoryDataset = categoryDataset;
    }

    public Chart(HttpServletResponse response) {
        this.response = response;
    }
}
