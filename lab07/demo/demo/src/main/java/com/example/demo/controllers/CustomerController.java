package com.example.demo.controllers;

import com.example.demo.charts.BarChart;
import com.example.demo.charts.PieChart;
import com.example.demo.databuilders.WriteAsJPG;
import com.example.demo.databuilders.customer.BuildClientsThatSpentMost;
import com.example.demo.databuilders.customer.BuildClientsThatWatchedMost;
import com.example.demo.databuilders.customer.BuildRentMoviesByMonth;
import com.example.demo.databuilders.customer.BuildRentMoviesByMonthByCustomer;
import com.example.demo.repositories.CustomerRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

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
    public ResponseEntity get10ClientsThatSpentMost() {
        return ResponseEntity.ok(repository.findTop10BySpentMost());
    }

    @RequestMapping(value = "ranking/bySpendMoney.jpg", params = "chart", method = GET)
    public void get10ClientsThatSpentMostChart(@RequestParam String chart, HttpServletResponse response) throws IOException {
        final PieDataset pieDataset = new BuildClientsThatSpentMost(repository).build10ClientsThatSpentMostChart();
        new PieChart(response, pieDataset).pieChart();
    }

    @RequestMapping(value = "ranking/byWatchedMovies", method = GET)
    public ResponseEntity get10ClientsThatWatchedMost() {
        return ResponseEntity.ok(repository.findTop10ByWatchedMost());
    }

    @RequestMapping(value = "ranking/byWatchedMovies.jpg", params = "chart", method = GET)
    public void get10ClientsThatWatchedMostChart(@RequestParam String chart, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = new BuildClientsThatWatchedMost(repository).build10ClientsThatWatchedMostChart();
        new BarChart(response, categoryDataset).barChart();
    }

    @RequestMapping(value = "activity/rentMoviesByMonth", params = "year", method = GET)
    public ResponseEntity getRentMoviesByMonth(@RequestParam String year) {
        return ResponseEntity.ok(repository.findRentMoviesByMonth(year));
    }

    @RequestMapping(value = "activity/rentMoviesByMonth.jpg", params = {"year", "chart"}, method = GET)
    public void getRentMoviesByMonthChart(@RequestParam String year, @RequestParam String chart, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = new BuildRentMoviesByMonth(repository, year).buildRentMoviesByMonthChart();
        new BarChart(response, categoryDataset).barChart();
    }

    @RequestMapping(value = "activity/rentMoviesByMonth", params = "customerid", method = GET)
    public ResponseEntity getRentMoviesByMonthByCustomer(@RequestParam String customerid) {
        return ResponseEntity.ok(repository.findRentMoviesByMonthByCustomer(customerid));
    }

    @RequestMapping(value = "activity/rentMoviesByMonth.jpg", params = {"customerid", "chart"}, method = GET)
    public void getRentMoviesByMonthByCustomerChart(@RequestParam String customerid, @RequestParam String chart, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = new BuildRentMoviesByMonthByCustomer(repository, customerid).buildRentMoviesByMonthByCustomerChart();
        new BarChart(response, categoryDataset).barChart();
    }
}
