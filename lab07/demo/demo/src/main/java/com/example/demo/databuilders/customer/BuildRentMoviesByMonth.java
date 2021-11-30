package com.example.demo.databuilders.customer;

import com.example.demo.repositories.CustomerRepository;
import org.jfree.data.category.DefaultCategoryDataset;

public class BuildRentMoviesByMonth extends BuildCustomer{
    String year;

    public BuildRentMoviesByMonth(CustomerRepository repository, String year) {
        super(repository);
        this.year = year;
    }

    public DefaultCategoryDataset buildRentMoviesByMonthChart() {
        final Comparable<String> rowKey = "Month";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        repository.findRentMoviesByMonth(year).forEach((data) -> categoryDataset.setValue(data.getRentmovies(), rowKey, String.valueOf(data.getMonth())));

        return categoryDataset;
    }
}
