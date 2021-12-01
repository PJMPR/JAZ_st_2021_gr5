package com.example.demo.databuilders.customer;

import com.example.demo.repositories.CustomerRepository;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class BuildClientsThatSpentMost extends BuildCustomer {
    public BuildClientsThatSpentMost(CustomerRepository repository) {
        super(repository);
    }

    public PieDataset build10ClientsThatSpentMostChart() {
        final DefaultPieDataset pieDataset = new DefaultPieDataset();
        repository.findTop10BySpentMost().forEach((data) -> pieDataset.setValue(data.getCustomer().getId(), data.getSpent()));
        return pieDataset;
    }
}
