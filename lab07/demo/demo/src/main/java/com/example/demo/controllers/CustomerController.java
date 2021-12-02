package com.example.demo.controllers;

import com.example.demo.repositories.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customers")
public class CustomerController {

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    CustomerRepository repository;

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        Timestamp t = Timestamp.valueOf("2021-01-10 00:00:00");
        return ResponseEntity.ok(repository.getById(id).getPayments().stream().map(x->x.getLastUpdate()).collect(Collectors.toList()));
    }

    @GetMapping("{customers/ranking/bySpentMoney}")
    public String display10TopClients() {
        return.
    }
}
