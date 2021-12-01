package com.example.demo.controllers;

import com.example.demo.charts.BarChart;
import com.example.demo.charts.PieChart;
import com.example.demo.databuilders.customer.BuildClientsThatSpentMost;
import com.example.demo.databuilders.customer.BuildClientsThatWatchedMost;
import com.example.demo.databuilders.customer.BuildRentMoviesByMonth;
import com.example.demo.databuilders.customer.BuildRentMoviesByMonthByCustomer;
import com.example.demo.projections.customer.IFindRentMoviesByMonth;
import com.example.demo.projections.customer.IFindRentMoviesByMonthByCustomer;
import com.example.demo.projections.customer.IFindTop10ByWatchedMost;
import com.example.demo.projections.customer.IFindTop10ThatSpentMost;
import com.example.demo.repositories.CustomerRepository;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("customers")
public class CustomerController {

    CustomerRepository repository;

    @Autowired
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "ranking/bySpendMoney", method = GET)
    public ResponseEntity<List<IFindTop10ThatSpentMost>> get10ClientsThatSpentMost() {
        return new ResponseEntity<>(repository.findTop10BySpentMost(), HttpStatus.OK);
    }

    @RequestMapping(value = "ranking/bySpendMoney.jpg", params = "chart", method = GET)
    public void get10ClientsThatSpentMostChart(@RequestParam String chart, HttpServletResponse response) throws IOException {
        final PieDataset pieDataset = new BuildClientsThatSpentMost(repository).build10ClientsThatSpentMostChart();
        new PieChart(response, pieDataset).pieChart();
    }

    @RequestMapping(value = "ranking/byWatchedMovies", method = GET)
    public ResponseEntity<List<IFindTop10ByWatchedMost>> get10ClientsThatWatchedMost() {
        return new ResponseEntity<>(repository.findTop10ByWatchedMost(), HttpStatus.OK);
    }

    @RequestMapping(value = "ranking/byWatchedMovies.jpg", params = "chart", method = GET)
    public void get10ClientsThatWatchedMostChart(@RequestParam String chart, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = new BuildClientsThatWatchedMost(repository).build10ClientsThatWatchedMostChart();
        new BarChart(response, categoryDataset).barChart();
    }

    @RequestMapping(value = "activity/rentMoviesByMonth", params = "year", method = GET)
    public ResponseEntity<List<IFindRentMoviesByMonth>> getRentMoviesByMonth(@RequestParam String year) {
        return new ResponseEntity<>(repository.findRentMoviesByMonth(year), HttpStatus.OK);
    }

    @RequestMapping(value = "activity/rentMoviesByMonth.jpg", params = {"year", "chart"}, method = GET)
    public void getRentMoviesByMonthChart(@RequestParam String year, @RequestParam String chart, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = new BuildRentMoviesByMonth(repository, year).buildRentMoviesByMonthChart();
        new BarChart(response, categoryDataset).barChart();
    }

    @RequestMapping(value = "activity/rentMoviesByMonth", params = "customerid", method = GET)
    public ResponseEntity<List<IFindRentMoviesByMonthByCustomer>> getRentMoviesByMonthByCustomer(@RequestParam String customerid) {
        return new ResponseEntity<>(repository.findRentMoviesByMonthByCustomer(customerid), HttpStatus.OK);
    }

    @RequestMapping(value = "activity/rentMoviesByMonth.jpg", params = {"customerid", "chart"}, method = GET)
    public void getRentMoviesByMonthByCustomerChart(@RequestParam String customerid, @RequestParam String chart, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = new BuildRentMoviesByMonthByCustomer(repository, customerid).buildRentMoviesByMonthByCustomerChart();
        new BarChart(response, categoryDataset).barChart();
    }
}
