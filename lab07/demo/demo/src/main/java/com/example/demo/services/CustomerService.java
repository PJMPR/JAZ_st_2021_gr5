package com.example.demo.services;
import com.example.demo.data.Customer;
import com.example.demo.ranking.CustomerData;
import com.example.demo.ranking.CustomerRanking;
import com.example.demo.ranking.RentalData;
import com.example.demo.ranking.RentalRanking;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class CustomerService {
    public CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<CustomerData> getCustomers(String criteria) {
        List<Customer> customerList = customerRepository.findAll();
        List<Customer> sortedCustomers = new ArrayList<>();
        if (criteria.equals("bySpentMoney")) {
            sortedCustomers = customerList.stream().sorted((c1, c2) -> Double.compare(c1.spentMoney(), c2.spentMoney())).limit(10).collect(Collectors.toList());
        } else if (criteria.equals("byWatchedMovies")) {
            sortedCustomers = customerList.stream().sorted((c1, c2) -> Integer.compare(c1.moviesWatched(), c2.moviesWatched())).limit(10).collect(Collectors.toList());
        }
        CustomerRanking ranking = new CustomerRanking(new ArrayList<>());
        sortedCustomers.forEach(customer -> ranking.addToRanking(customer));
        return ranking.getRankedCustomersList();
    }

    public List<RentalData> getAllCustomersMonthlyRentals(int year, Integer id) {
        List<Customer> customerList;
        if (id == null) {
            customerList = customerRepository.findAll();
        } else {
            customerList = customerRepository.findById(id).stream().toList();
        }
        RentalRanking ranking = new RentalRanking(new ArrayList<>());
        for (int month = 1; month <= 12; month++) {
            int rentalsByMonth = 0;
            for (Customer customer : customerList) {
                rentalsByMonth += customer.rentalsByRentalDate(year, month);
            }
            ranking.addToRentalRanking(month, rentalsByMonth);
        }
        return ranking.getRankedRentalList();
    }
}


