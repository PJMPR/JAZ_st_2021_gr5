package com.example.demo.ranking;

import com.example.demo.data.Customer;

import java.util.List;
import java.util.TreeMap;

public class CustomersRankedBySpentMoney {
    private  List<CustomerData> top10customers;

    public void addToRanking(Customer customer){
       int id= customer.getCustomerId();
       String name= customer.getFirstName();
       String surname= customer.getLastName();
       double amount=customer.AmountSpent();
       int movies_watched=customer.moviesWatched();

       top10customers.add(new CustomerData(id,name,surname,amount,movies_watched));
    }

    public CustomersRankedBySpentMoney(List<CustomerData> top10customers) {
        this.top10customers = top10customers;
    }

    public List<CustomerData> getTop10customers() {
        return top10customers;
    }

    public void setTop10customers(List<CustomerData> top10customers) {
        this.top10customers = top10customers;
    }
}
