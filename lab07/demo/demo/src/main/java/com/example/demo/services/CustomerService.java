package com.example.demo.services;

import com.example.demo.charts.BarChartGenerator;
import com.example.demo.charts.IChartGenerator;
import com.example.demo.charts.PieChartGenerator;
import com.example.demo.data.CustomerStats;
import com.example.demo.data.MonthStats;
import com.example.demo.repositories.CustomerRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerService {
    public CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public CustomerService(){}

    private List<CustomerStats> getTopCustomerStats(){
        ArrayList<CustomerStats> customerStatsList = new ArrayList<>();
        customerRepository.findAll().forEach(
            x -> customerStatsList.add(new CustomerStats(x.getCustomerId(), x.getFirstName(), x.getLastName(), x.amountSpent(), x.moviesWatched()))
        );
        return customerStatsList;
    }

    public List<CustomerStats> getTopMoneySpent(int limit){
        return getTopCustomerStats().stream()
                .sorted(Comparator.comparing(CustomerStats::getAmountSpent).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<CustomerStats> getTopWatchedMovies(int limit){
        return getTopCustomerStats().stream()
                .sorted(Comparator.comparingInt(CustomerStats::getMoviesWatched).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Object getTopMoneySpentChart(int limit) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        getTopMoneySpent(limit).stream().forEach(x -> dataset.setValue(x.getId(), x.getAmountSpent()));
        JFreeChart chart = ChartFactory.createPieChart(
                "TopMoneySpent",
                dataset,
                true,
                true,
                false);
        return chart;
    }

    public Object getTopWatchedMoviesChart(int limit){
        DefaultPieDataset dataset = new DefaultPieDataset();
        getTopWatchedMovies(limit).stream().forEach(x -> dataset.setValue(String.valueOf(x.getId()), x.getMoviesWatched()));
        JFreeChart chart = ChartFactory.createPieChart(
                "TopWatchedMovies",
                dataset,
                true,
                true,
                false);
        return chart;
    }

    public List<MonthStats> getMoviesByMonth(int year) {
        ArrayList<MonthStats> monthStats = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        IntStream.rangeClosed(1, 12).forEach(i -> temp.add(customerRepository.findAll().stream().map(x -> x.getRentalsByYear(year, i)).reduce(0, Integer::sum)));
        IntStream.rangeClosed(1, 12).forEach(i -> monthStats.add(new MonthStats(String.valueOf(i), temp.get(i-1))));
        return monthStats;
    }

    public Object getMoviesForCustomer(int year, int id) {
        ArrayList<MonthStats> monthStats = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        IntStream.rangeClosed(1, 12).forEach(i -> temp.add(customerRepository.findById(id).stream().map(x -> x.getRentalsByYear(year, i)).reduce(0, Integer::sum)));
        IntStream.rangeClosed(1, 12).forEach(i -> monthStats.add(new MonthStats(String.valueOf(i), temp.get(i-1))));
        return monthStats;
    }

    public byte[] generateCustomerPieChart(String title, String field, List<CustomerStats> entryData) throws IOException {
        IChartGenerator pieChartGenerator = new PieChartGenerator();
        DefaultPieDataset dataset = (DefaultPieDataset) pieChartGenerator.getDataset();

        switch (field) {
            case "movies":
                entryData.forEach(customerStats -> dataset.setValue(
                        customerStats.getId(),
                        (Number) customerStats.getMoviesWatched()));
                break;
            case "money":
                entryData.forEach(customerStats -> dataset.setValue(customerStats.getId(),
                        customerStats.getAmountSpent()));
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
                        (Number) customerStats.getMoviesWatched(),
                        customerStats.getId(),
                        "Customers"));
                break;
            case "money":
                entryData.forEach(customerStats -> dataset.setValue(
                        customerStats.getAmountSpent(),
                        customerStats.getId(),
                        "Customers"));
                break;
        }
        return barChartGenerator.generate(title, "bar", xAxis, yAxis);
    }

    public byte[] generateRentalBarChart(String title, String xAxis, String yAxis, List<MonthStats> entryData) throws IOException {
        BarChartGenerator barChartGenerator = new BarChartGenerator();
        DefaultCategoryDataset dataset = (DefaultCategoryDataset) barChartGenerator.getDataset();

        entryData.forEach(stats -> dataset.setValue(
                (Number) stats.getValue(),
                "rentals",
                stats.getMonth()
        ));

        return barChartGenerator.generate(title, "bar", xAxis, yAxis);
    }

    public byte[] generateRentalPieChart(String title, List<MonthStats> entryData) throws IOException {
        IChartGenerator pieChartGenerator = new PieChartGenerator();
        DefaultPieDataset dataset = (DefaultPieDataset) pieChartGenerator.getDataset();

        entryData.forEach(stats -> dataset.setValue(
                stats.getMonth(),
                (Number) stats.getValue())
        );

        return pieChartGenerator.generate(title, "pie", "", "");
    }
}
