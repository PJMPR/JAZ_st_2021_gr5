package com.example.demo.databuilders.customer;

import com.example.demo.repositories.CustomerRepository;
import org.jfree.data.category.DefaultCategoryDataset;

public class BuildClientsThatWatchedMost extends BuildCustomer{
    public BuildClientsThatWatchedMost(CustomerRepository repository) {
        super(repository);
    }

    public DefaultCategoryDataset build10ClientsThatWatchedMostChart() {
        final Comparable<String> rowKey = "Customer ID";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        repository.findTop10ByWatchedMost().forEach((data) -> categoryDataset.setValue(data.getWatched(), rowKey, String.valueOf(data.getCustomer().getId())));

        return categoryDataset;
    }
}
