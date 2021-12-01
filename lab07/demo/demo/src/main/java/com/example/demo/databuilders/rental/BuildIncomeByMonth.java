package com.example.demo.databuilders.rental;

import com.example.demo.repositories.RentalRepository;
import org.jfree.data.category.DefaultCategoryDataset;

public class BuildIncomeByMonth extends BuildRental {
    String year;

    public BuildIncomeByMonth(RentalRepository repository, String year) {
        super(repository);
        this.year = year;
    }


    public DefaultCategoryDataset buildIncomeByMonthChart() {
        final Comparable<String> rowKey = "Month";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        repository.incomeByMonth(year).forEach(data -> categoryDataset.setValue(data.getIncome(), rowKey, data.getMonth()));

        return categoryDataset;
    }
}
