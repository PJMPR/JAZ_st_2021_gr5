package com.example.demo.databuilders.rental;

import com.example.demo.repositories.RentalRepository;
import org.jfree.data.category.DefaultCategoryDataset;

public class BuildIncome extends BuildRental {
    String from;
    String to;

    public BuildIncome(RentalRepository repository, String from, String to) {
        super(repository);
        this.from = from;
        this.to = to;
    }

    public DefaultCategoryDataset buildIncomeChart() {
        final Comparable<String> rowKey = "Month";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        repository.incomeByMonthFromTo(from, to).forEach(data -> categoryDataset.setValue(data.getIncome(), rowKey, data.getMonth()));

        return categoryDataset;
    }
}
