package com.example.demo.databuilders.customer;

import com.example.demo.repositories.CustomerRepository;
import org.jfree.data.category.DefaultCategoryDataset;

public class BuildRentMoviesByMonthByCustomer extends BuildCustomer {
    String id;

    public BuildRentMoviesByMonthByCustomer(CustomerRepository repository, String id) {
        super(repository);
        this.id = id;
    }

    public DefaultCategoryDataset buildRentMoviesByMonthByCustomerChart() {
        final Comparable<String> rowKey = "Month";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        repository.findRentMoviesByMonthByCustomer(id).forEach((data) -> categoryDataset.setValue(data.getRentmovies(), rowKey, String.valueOf(data.getMonth())));

        return categoryDataset;
    }
}
