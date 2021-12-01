package com.example.demo.rankings;

import com.example.demo.data.Customer;
import com.example.demo.data.CustomerData;

import java.util.ArrayList;
import java.util.List;

public class Ranking {
    private List<CustomerData> customerRanking;

    public Ranking(List<CustomerData> customerRanking) {
        this.customerRanking = customerRanking;
    }

    public void addToRanking(Customer customer){
        customerRanking.add(new CustomerData(customer.getCustomerId(),customer.getFirstName(),
                customer.getLastName(),customer.amountSpent(),customer.moviesWatched()));
    }

    public List<CustomerData> getCustomerRanking() {
        return customerRanking;
    }

    public void setCustomerRanking(List<CustomerData> customerRanking) {
        this.customerRanking = customerRanking;
    }
}
