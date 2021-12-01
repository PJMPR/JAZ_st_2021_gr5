package com.example.demo.services;

import com.example.demo.data.Customer;
import com.example.demo.data.CustomerData;
import com.example.demo.rankings.Ranking;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Ranking getCustomersByMoneySpent(int limit) {
        List<Customer> customerList = customerRepository.findAll();
        List<Customer> topCustomersList = customerList.stream()
                .sorted((c2, c1) -> Double.compare(c1.amountSpent(), c2.amountSpent()))
                .limit(limit).collect(Collectors.toList());
        Ranking ranking = new Ranking(new ArrayList<>());
        topCustomersList.forEach(ranking::addToRanking);
        return ranking;
    }

    public Ranking getCustomersByWatchedMovies(int limit) {
        List<Customer> customerList = customerRepository.findAll();
        List<Customer> topCustomersList = customerList.stream()
                .sorted((c2, c1) -> Double.compare(c1.moviesWatched(), c2.moviesWatched()))
                .limit(limit).collect(Collectors.toList());
        Ranking ranking = new Ranking(new ArrayList<>());
        topCustomersList.forEach(ranking::addToRanking);
        return ranking;
    }
}
