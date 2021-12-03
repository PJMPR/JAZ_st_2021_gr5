package com.example.demo.service;

import com.example.demo.charts.BarChartGenerator;
import com.example.demo.charts.IChartGenerator;
import com.example.demo.charts.LinearChartGenerator;
import com.example.demo.charts.PieChartGenerator;
import com.example.demo.model.*;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.RentalRepository;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerService {

    CustomerRepository repository;
    RentalRepository rentalRepository;


    public CustomerService(CustomerRepository repository, RentalRepository rentalRepository) {
        this.repository = repository;
        this.rentalRepository = rentalRepository;
    }


    public List<CustomerStats> getCustomerStats() {
        return repository.findAll().stream()
                .map(customer ->
                        new CustomerStats(
                                customer.getCustomerId(),
                                customer.getFirstName(),
                                customer.getLastName(),
                                customer.amountSpent(),
                                customer.moviesWatched())
                )
                .collect(Collectors.toList());
    }

    public List<CustomerStats> rankCustomersByMoneySpent() {
        return getCustomerStats().stream()
                .sorted(Comparator.comparing(CustomerStats::getMoneySpent).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<CustomerStats> rankCustomersByWatchedMovies() {
        return getCustomerStats().stream()
                .sorted(Comparator.comparing(CustomerStats::getMoneySpent).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<RentalStats> rentMoviesByMonths(int year) {
        List<RentalStats> rentalStats = new ArrayList<>();
        List<Integer> sumOfRentals = new ArrayList<>();

        IntStream.rangeClosed(1, 12).forEach(i -> sumOfRentals.add(repository.findAll().stream().map(x -> x.getRentalsByMonth(year, i)).reduce(0, Integer::sum)));
        IntStream.rangeClosed(1, 12).forEach(i -> rentalStats.add(new RentalStats(i, sumOfRentals.get(i - 1))));

        return rentalStats;
    }

    public Object getMoviesForCustomer(int year, int id) {
        ArrayList<RentalStats> monthStats = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        IntStream.rangeClosed(1, 12).forEach(i -> temp.add(repository.findById(id).stream().map(x -> x.getRentalsByMonth(year, i)).reduce(0, Integer::sum)));
        IntStream.rangeClosed(1, 12).forEach(i -> monthStats.add(new RentalStats(i, temp.get(i-1))));
        return monthStats;
    }

    public byte[] generateCustomerPieChart(String title, String field, List<CustomerStats> entryData) throws IOException {
        IChartGenerator pieChartGenerator = new PieChartGenerator();
        DefaultPieDataset dataset = (DefaultPieDataset) pieChartGenerator.getDataset();

        switch (field) {
            case "movies":
                entryData.forEach(customerStats -> dataset.setValue(
                        customerStats.getCustomerId(),
                        (Number) customerStats.getWatchedMovies()));
                break;
            case "money":
                entryData.forEach(customerStats -> dataset.setValue(customerStats.getCustomerId(),
                        customerStats.getMoneySpent()));
                break;
        }

        return pieChartGenerator.generate(title, "pie", "", "");
    }


    public byte[] generateCustomerBarChart(String title, String field, String xAxis, String yAxis, List<CustomerStats> entryData) throws IOException {
        BarChartGenerator barChartGenerator = new BarChartGenerator();
        DefaultCategoryDataset dataset = (DefaultCategoryDataset) barChartGenerator.getDataset();
        switch (field) {
            case "movies":

                entryData.forEach(customerStats -> dataset.setValue(
                        (Number) customerStats.getWatchedMovies(),
                        customerStats.getCustomerId(),
                        "Customers"));
                break;
            case "money":
                entryData.forEach(customerStats -> dataset.setValue(
                        customerStats.getMoneySpent(),
                        customerStats.getCustomerId(),
                        "Customers"));
                break;
        }
        return barChartGenerator.generate(title, "bar", xAxis, yAxis);
    }


    public byte[] generateRentalBarChart(String title, String xAxis, String yAxis, List<RentalStats> entryData) throws IOException {
        BarChartGenerator barChartGenerator = new BarChartGenerator();
        DefaultCategoryDataset dataset = (DefaultCategoryDataset) barChartGenerator.getDataset();

        entryData.forEach(stats -> dataset.setValue(
                (Number) stats.getRentValue(),
                "rentals",
                stats.getMonth()
        ));

        return barChartGenerator.generate(title, "bar", xAxis, yAxis);
    }

    public byte[] generateRentalPieChart(String title, List<RentalStats> entryData) throws IOException {
        IChartGenerator pieChartGenerator = new PieChartGenerator();
        DefaultPieDataset dataset = (DefaultPieDataset) pieChartGenerator.getDataset();

        entryData.forEach(stats -> dataset.setValue(
                stats.getMonth(),
                (Number) stats.getRentValue())
        );

        return pieChartGenerator.generate(title, "pie", "", "");
    }



//    public byte[] generateBarChart(Number value, int key,String columnKey,String title,String xAxis, String yAxis) throws IOException {
//        BarChartGenerator barChartGenerator = new BarChartGenerator();
//        DefaultCategoryDataset dataset = (DefaultCategoryDataset) barChartGenerator.getDataset();
//
//        dataset.setValue(value, key, columnKey);
//
//        return barChartGenerator.generate(title, "bar", xAxis, yAxis);
//    }

}
