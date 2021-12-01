package com.example.demo.services;

import com.example.demo.data.CustomerStats;
import com.example.demo.repositories.CustomerRepository;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerService {
    public CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public CustomerService(){

    }

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

    public Object getTopMoneySpentChart(int limit, String chartType) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        getTopMoneySpent(limit).stream().forEach(x -> dataset.setValue(x.getId(), x.getAmountSpent()));
        return dataset;
    }
}
