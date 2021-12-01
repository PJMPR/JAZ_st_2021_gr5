package com.example.demo.controllers;

import com.example.demo.charts.CanvasjsChartService;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    CustomerRepository repository;
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity getCustomer(@PathVariable("id") int id){
        Timestamp t = Timestamp.valueOf("2021-01-10 00:00:00");
        return ResponseEntity.ok(repository.getById(id).getPayments().stream().map(x->x.getLastUpdate()).collect(Collectors.toList()));
    }

    @GetMapping
    @RequestMapping("/ranking/bySpentMoney")
    public ResponseEntity displayCustomerRankingBySpentMoney(){
        return ResponseEntity.ok(customerService.getCustomersByMoneySpent(10));
    }
    @Autowired
    private CanvasjsChartService canvasjsChartService;

    @RequestMapping("/ranking/bySpentMoney.jpg?chart=pie")
    public String springMVC(ModelMap modelMap) {
        List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData();
        modelMap.addAttribute("dataPointsList", canvasjsDataList);
        return "chart";
    }

    @GetMapping
    @RequestMapping("/ranking/byWatchedMovies")
    public ResponseEntity displayCustomerRankingByWatchedMovies(){
        return ResponseEntity.ok(customerService.getCustomersByWatchedMovies(10));
    }

//    @GetMapping
//    @RequestMapping("/activity/rentMoviesByMonth")
//    public ResponseEntity displayRentMoviesByMonth(year){
//        return ResponseEntity.ok(getRentMoviesByMonth(year));
//    }
}
