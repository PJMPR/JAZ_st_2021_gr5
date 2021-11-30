package com.example.demo.service;

import com.example.demo.data.Customer;
import com.example.demo.data.Payment;
import com.example.demo.ranking.CustomerData;
import com.example.demo.ranking.CustomerRanking;
import com.example.demo.ranking.CustomersRankedBySpentMoney;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomerService {


    public CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerService() {
    }

    public Customer getById(int id) {
        return customerRepository.getById(id);
    }

    public List<CustomerData> getRankingByMoneySpent() {

        List<Customer> customerList = customerRepository.findAll();
        List<Customer> top10 = customerList.stream()
                .sorted((c2, c1) -> Double.compare(c1.AmountSpent(), c2.AmountSpent()))
                .limit(10).collect(Collectors.toList());
        CustomersRankedBySpentMoney ranking = new CustomersRankedBySpentMoney(new ArrayList<>());
        top10.forEach(ranking::addToRanking);

        return ranking.getTop10customers();
    }

    public List<CustomerData> getRankingByMoviesWatched() {
        List<Customer> customerList = customerRepository.findAll();
        List<Customer> top10 = customerList.stream()
                .sorted((c2, c1) -> Integer.compare(c1.moviesWatched(), c2.moviesWatched()))
                .limit(10).collect(Collectors.toList());
        CustomersRankedBySpentMoney ranking = new CustomersRankedBySpentMoney(new ArrayList<>());
        top10.forEach(ranking::addToRanking);

        return ranking.getTop10customers();
    }

}
