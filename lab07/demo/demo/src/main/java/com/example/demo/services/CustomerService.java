package com.example.demo.services;

import com.example.demo.data.CustomerStats;
import com.example.demo.data.MonthStats;
import com.example.demo.repositories.CustomerRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerService {
    public CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public CustomerService(){}

    private List<CustomerStats> getTopCustomerStats(){
        ArrayList<CustomerStats> customerStatsList = new ArrayList<>();
        customerRepository.findAll().forEach(
            x -> customerStatsList.add(new CustomerStats(x.getCustomerId(), x.getFirstName(), x.getLastName(), x.amountSpent(), x.moviesWatched()))
        );
        return customerStatsList;
    }

    public List<CustomerStats> getTopMoneySpent(int limit){
        return getTopCustomerStats().stream()
                .sorted(Comparator.comparing(CustomerStats::getAmountSpent).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CustomerStats> getTopWatchedMovies(int limit){
        return getTopCustomerStats().stream()
                .sorted(Comparator.comparingInt(CustomerStats::getMoviesWatched).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Object getTopMoneySpentChart(int limit) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        getTopMoneySpent(limit).stream().forEach(x -> dataset.setValue(x.getId(), x.getAmountSpent()));
        JFreeChart chart = ChartFactory.createPieChart(
                "TopMoneySpent",
                dataset,
                true,
                true,
                false);
        return chart;
    }

    public Object getTopWatchedMoviesChart(int limit){
        DefaultPieDataset dataset = new DefaultPieDataset();
        getTopWatchedMovies(limit).stream().forEach(x -> dataset.setValue(String.valueOf(x.getId()), x.getMoviesWatched()));
        JFreeChart chart = ChartFactory.createPieChart(
                "TopWatchedMovies",
                dataset,
                true,
                true,
                false);
        return chart;
    }

    public List<MonthStats> getMoviesByMonth(int year) {
        ArrayList<MonthStats> monthStats = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        IntStream.rangeClosed(1, 12).forEach(i -> temp.add(customerRepository.findAll().stream().map(x -> x.getRentalsByYear(year, i)).reduce(0, Integer::sum)));
        IntStream.rangeClosed(1, 12).forEach(i -> monthStats.add(new MonthStats(i, temp.get(i-1))));
        return monthStats;
    }
}
