package com.example.demo.ranking;
import com.example.demo.data.Customer;

import java.util.List;

public class CustomerRanking {
    private final List<CustomerData> rankedCustomers;

    public void addToRanking(Customer customer) {
        int id = customer.getCustomerId();
        String name = customer.getFirstName();
        String surname = customer.getLastName();
        double spent = customer.spentMoney();
        int watched = customer.moviesWatched();

        rankedCustomers.add(new CustomerData(id, name, surname, spent, watched));
    }

    public CustomerRanking(List<CustomerData> rankedCustomers) {
        this.rankedCustomers = rankedCustomers;
    }

    public List<CustomerData> getRankedCustomersList() {
        return rankedCustomers;
    }
}
