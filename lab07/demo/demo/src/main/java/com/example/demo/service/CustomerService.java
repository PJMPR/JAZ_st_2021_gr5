package com.example.demo.service;

import com.example.demo.charts.BarChartGenerator;
import com.example.demo.charts.PieChartGenerator;
import com.example.demo.data.Customer;
import com.example.demo.data.Payment;
import com.example.demo.data.Rental;
import com.example.demo.model.customerSpentMoney;
import com.example.demo.model.customerWatchedMovies;
import com.example.demo.model.ranking;
import com.example.demo.model.rentMoviesByMonth;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.RentalRepository;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomerService {

    CustomerRepository repository;
    RentalRepository rentalRepository;
    ranking ranking = new ranking();

    public CustomerService(CustomerRepository repository, RentalRepository rentalRepository) {
        this.repository = repository;
        this.rentalRepository = rentalRepository;
    }

    //public final List<Integer> ids = repository.findAll().stream().map(Customer::getCustomerId).collect(Collectors.toList());

    public List<customerSpentMoney> rankCustomersByMoneySpent() {
        Map<Integer, BigDecimal> myMap = new HashMap<>();
        Map<Integer, BigDecimal> finalMyMap = myMap;
        repository.findAll().stream().map(Customer::getCustomerId).forEach(id -> finalMyMap.put(id, repository.getById(id).getPayments().stream().map(Payment::getAmount).reduce(BigDecimal.valueOf(0), BigDecimal::add)));

        myMap = myMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        myMap.keySet().stream().limit(10).forEach(key -> ranking.getRanking()
                .add(new customerSpentMoney(repository.getById(key).getCustomerId(),
                        repository.getById(key).getFirstName(),
                        repository.getById(key).getLastName(),
                        finalMyMap.get(key))));

        return ranking.getRanking();
    }

    public List<customerWatchedMovies> rankCustomersByWatchedMovies() {
        Map<Integer, Integer> myMap = new HashMap<>();
        //List<Integer> ids = repository.findAll().stream().map(Customer::getCustomerId).collect(Collectors.toList());

        Map<Integer, Integer> finalMyMap = myMap;
        repository.findAll().stream().map(Customer::getCustomerId).forEach(id-> finalMyMap.put(id, repository.getById(id).getRentalsByCustomer().size()));


        myMap = myMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        myMap.keySet().stream().limit(10)
                .forEach(key -> ranking.getRankingMovies()
                        .add(new customerWatchedMovies(repository.getById(key).getCustomerId(),
                                repository.getById(key).getFirstName(),
                                repository.getById(key).getLastName(),
                                finalMyMap.get(key))));
        return ranking.getRankingMovies();
    }

    public List<rentMoviesByMonth> rentMoviesByMonths(int year) {
        Map<Integer, Integer> myMap = new HashMap<>();
        List<Integer> ids = rentalRepository.findAll().stream().map(Rental::getRentalId).collect(Collectors.toList());

        for (int id : ids) {
           // if (rentalRepository.findById(id).get().getRentalDate().getYear() == 2005) {
                if (!myMap.containsKey(rentalRepository.findById(id).get().getRentalDate().getMonth())) {
                    myMap.put(rentalRepository.findById(id).get().getRentalDate().getMonth(), 1);
                } else {
                    myMap.put(rentalRepository.findById(id).get().getRentalDate().getMonth(), myMap.get(rentalRepository.findById(id).get().getRentalDate().getMonth()) + 1);
                }
           // }
        }
        myMap.keySet()
                .forEach(key -> ranking.getRentMoviesByMonths()
                        .add(new rentMoviesByMonth(key,
                                myMap.get(key))));

        return ranking.getRentMoviesByMonths();
    }

    public byte[] generatePieChart() throws IOException {
        PieChartGenerator pieChartGenerator = new PieChartGenerator();
        DefaultPieDataset dataset = pieChartGenerator.getDataset();

        List<customerSpentMoney> entryData = rankCustomersByMoneySpent();

        entryData.forEach(customerSpentMoney -> dataset.setValue(customerSpentMoney.getCustomerId(), customerSpentMoney.getMoneySpent()));

        return pieChartGenerator.generate("Customers by Money Spent");
    }

    public byte[] generateBarChart() throws IOException {
        BarChartGenerator barChartGenerator = new BarChartGenerator();
        DefaultCategoryDataset dataset = barChartGenerator.getDataset();
        List<customerWatchedMovies> entryData = rankCustomersByWatchedMovies();

        entryData.forEach(customerWatchedMovies -> dataset.setValue((Number) customerWatchedMovies.getWatchedMovies(), customerWatchedMovies.getCustomerId(), "Customers"));

        return barChartGenerator.generate("Movies Watched", "", "movies");
    }
}
