package com.example.demo.services;

import com.example.demo.charts.BarChart;
import com.example.demo.charts.ChartType;
import com.example.demo.charts.PieChart;
import com.example.demo.data.Customer;

import com.example.demo.rankings.Ranking;
import com.example.demo.repositories.CustomerRepository;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Ranking getCustomersByMoneySpent(int limit) {
        List<Customer> customerList = customerRepository.findAll();
        List<Customer> topCustomersList = customerList.stream()
                .sorted((c2, c1) -> Double.compare(c1.amountSpent(), c2.amountSpent()))
                .limit(limit).collect(Collectors.toList());
        Ranking ranking = new Ranking(new ArrayList<>());
        topCustomersList.forEach(ranking::addToRanking);
        return ranking;
    }

    public Ranking getCustomersByWatchedMovies(int limit) {
        List<Customer> customerList = customerRepository.findAll();
        List<Customer> topCustomersList = customerList.stream()
                .sorted((c2, c1) -> Double.compare(c1.moviesWatched(), c2.moviesWatched()))
                .limit(limit).collect(Collectors.toList());
        Ranking ranking = new Ranking(new ArrayList<>());
        topCustomersList.forEach(ranking::addToRanking);
        return ranking;
    }

    public byte[] makeChart(ChartType chartType) throws IOException {
        if (chartType==ChartType.PIE){
            PieChart pieChart = new PieChart();
            DefaultPieDataset dataset = pieChart.getDataset();
            Ranking chartData = getCustomersByMoneySpent(10);
            chartData.getCustomerRanking().forEach(customer -> dataset.setValue(customer.getId(), customer.getAmountSpent()));
            return pieChart.generate("Customers by money spent");
        }else if(chartType==ChartType.BAR){
            BarChart barChart = new BarChart();
            DefaultCategoryDataset dataset = barChart.getDataset();
            Ranking chartData = getCustomersByWatchedMovies(10);
            chartData.getCustomerRanking().forEach(customerWatchedMovies -> dataset.setValue((Number) customerWatchedMovies.getMoviesWatched(), customerWatchedMovies.getId(), "Customers"));
            return barChart.generate("Customers by movies watched", "", "movies watched");
        }
        return null;
    }
}
