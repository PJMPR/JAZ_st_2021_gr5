package com.example.demo.service;

import com.example.demo.data.Customer;
import com.example.demo.data.Payment;
import com.example.demo.model.customerSpentMoney;
import com.example.demo.model.ranking;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomerService {

    CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public ranking rankCustomersByMoneySpent(){
        Map<Integer, BigDecimal> myMap = new HashMap<>();
        List<Integer> ids = repository.findAll().stream().map(Customer::getCustomerId).collect(Collectors.toList());
        for (int id : ids) {
            myMap.put(id, repository.getById(id).getPayments().stream().map(Payment::getAmount).reduce(BigDecimal.valueOf(0), BigDecimal::add));
        }
        myMap = myMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
                (oldValue,newValue) -> oldValue, LinkedHashMap::new));

        Map<Integer, BigDecimal> finalMyMap1 = myMap;
        ranking ranking = new ranking();
        myMap.keySet().stream().limit(10).forEach(key->ranking.getRanking().add(new customerSpentMoney(repository.getById(key).getCustomerId(),repository.getById(key).getFirstName(),repository.getById(key).getLastName(), finalMyMap1.get(key))));

        return ranking;
    }
}
