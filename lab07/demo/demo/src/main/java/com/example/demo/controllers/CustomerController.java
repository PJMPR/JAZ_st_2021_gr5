package com.example.demo.controllers;

import com.example.demo.data.Customer;
import com.example.demo.data.Payment;
import com.example.demo.model.customerSpentMoney;
import com.example.demo.model.customerWatchedMovies;
import com.example.demo.model.ranking;
import com.example.demo.model.rentMoviesByMonth;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("customers")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerRepository repository,CustomerService service) {
       // this.repository = repository;
        this.customerService = service;
    }

   // CustomerRepository repository;

//    @GetMapping
//    @RequestMapping("{id}")
//    public ResponseEntity get(@PathVariable("id") int id) {
//        Timestamp t = Timestamp.valueOf("2021-01-10 00:00:00");
//        return ResponseEntity.ok(repository.getById(id).getPayments().stream().map(x -> x.getLastUpdate()).collect(Collectors.toList()));
//    }

    @GetMapping
    @RequestMapping("ranking/bySpentMoney")
    public ResponseEntity<List<customerSpentMoney>> getByMoney() {
        return ResponseEntity.ok(customerService.rankCustomersByMoneySpent());
    }

    @GetMapping
    @RequestMapping("ranking/bySpentMoney.jpg/{chart}")
    public ResponseEntity getByMoneyChart(@PathVariable("chart") String chart) throws IOException {
        if(chart.equals("pie")){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(customerService.generatePieChart());
        }
        return ResponseEntity.ok(HttpStatus.NOT_IMPLEMENTED);
    }



    @GetMapping
    @RequestMapping("ranking/byWatchedMovies")
    public ResponseEntity<List<customerWatchedMovies>> getByWatchedMovies() {
        return ResponseEntity.ok(customerService.rankCustomersByWatchedMovies());
    }

    @GetMapping
    @RequestMapping("ranking/byWatchedMovies.jpg/{chart}")
    public ResponseEntity getByMoviesChart(@PathVariable("chart") String chart) throws IOException {
        if(chart.equals("bar")){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(customerService.generateBarChart());
        }
        return ResponseEntity.ok(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping
    @RequestMapping("ranking/rentMoviesByMonth/{year}")
    public ResponseEntity<List<rentMoviesByMonth>> getRentMoviesBymonth(@PathVariable("year") int year) {
        return ResponseEntity.ok(customerService.rentMoviesByMonths(year));
    }
}
