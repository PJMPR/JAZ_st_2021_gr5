package com.example.demo.controllers;

import com.example.demo.data.Customer;
import com.example.demo.ranking.CustomerData;
import com.example.demo.ranking.RentalData;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.CustomerService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customers")
public class CustomerController {

    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @RequestMapping("/ranking/bySpentMoney")
    public ResponseEntity<List<CustomerData>> getFirstTenCustomersBySpentMoney(){
        return ResponseEntity.ok(customerService.getCustomers("bySpentMoney"));
    }

    @GetMapping
    @RequestMapping("/ranking/byWatchedMovies")
    public ResponseEntity<List<CustomerData>> getFirstTenCustomersByWatchedMovied() {
        return ResponseEntity.ok(customerService.getCustomers("byWatchedMovies"));
    }

    @GetMapping
    @RequestMapping("/activity/rentMoviesByMonth")
    public ResponseEntity<List<RentalData>> getRentMoviesByMonth(@RequestParam int year, @RequestParam(required = false) Integer id) {
        return ResponseEntity.ok(customerService.getAllCustomersMonthlyRentals(year, id));
    }
}
