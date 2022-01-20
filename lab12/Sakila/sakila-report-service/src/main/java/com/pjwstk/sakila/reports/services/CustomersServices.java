package com.pjwstk.sakila.reports.services;

import com.pjwstk.sakila.data.repositories.CustomersRepository;
import com.pjwstk.sakila.logic.charts.ChartCreation;
import com.pjwstk.sakila.logic.charts.data.Series;
import com.pjwstk.sakila.logic.charts.data.SeriesValue;
import com.pjwstk.sakila.reports.contracts.CustomerDto;
import com.pjwstk.sakila.reports.contracts.CustomerValueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomersServices {
    private final CustomersRepository customersRepository;
    private final ChartCreation chartCreator;

    public List<CustomerValueDto<Double>> getCustomersWithMostSpentMoney() {
        var customers = customersRepository
                .customersThatSpentMoney(10)
                ;
        var result = customers.stream()
                .map(c->
                        new CustomerValueDto<Double>(
                                c.spentMoney(),
                                new CustomerDto(c.getId(), c.getFirstName(), c.getLastName())))
                .collect(Collectors.toList());
        return result;
    }

    public byte[] getChartForMostSpent(RequestedChartTypes chartType)  {
        var customers = getCustomersWithMostSpentMoney();
        var series =
                customers
                        .stream()
                        .map(x->new SeriesValue(
                                String.format("%s %s",x.getCustomer().getFirstName(),x.getCustomer().getLastName()),
                                x.getValue()))
                        .collect(Collectors.toList())
                ;
        return chartType
                .getBytes(chartCreator, new Series("Most Spent", series));
    }



}
