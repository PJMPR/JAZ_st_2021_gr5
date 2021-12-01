package com.example.demo.services;

import com.example.demo.data.Customer;
import com.example.demo.data.CustomerStats;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
            x -> customerStatsList.add(new CustomerStats(x, x.amountSpent(), x.moviesWatched()))
        );
        return customerStatsList;
    }

    public List<CustomerStats> getTopMoneySpent(int limit){
        return getTopCustomerStats().stream()
                .sorted(Comparator.comparingDouble(CustomerStats::getAmountSpent))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CustomerStats> getTopWatchedMovies(int limit){
        return getTopCustomerStats().stream()
                .sorted(Comparator.comparingInt(CustomerStats::getMoviesWatched))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
