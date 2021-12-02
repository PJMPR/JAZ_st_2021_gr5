package com.example.demo.services;

import com.example.demo.data.MonthStats;
import com.example.demo.data.Rental;
import com.example.demo.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class RentalService {
    public RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository){
        this.rentalRepository = rentalRepository;
    }

    public RentalService(){}

    public int getIncomeByYear(int year, int month) {
        Timestamp timeFrom = Timestamp.valueOf(year+"-"+month+"-01 00:00:01");
        Timestamp timeTo = Timestamp.valueOf(year+"-"+month+"-31 23:59:59");
        return rentalRepository.findAll().stream()
                .map(Rental::getPaymentsByRentalId)
                .map(x -> x.stream().filter(p -> p.getPaymentDate().after(timeFrom) && p.getPaymentDate().before(timeTo)))
                .map(x -> x.map(p -> p.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add).intValue())
                .reduce(0, Integer::sum);
    }

    public List<MonthStats> getIncomeByMonth(int year) {
        ArrayList<MonthStats> monthStats = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        IntStream.rangeClosed(1, 12).forEach(i -> temp.add(getIncomeByYear(year, i)));
        IntStream.rangeClosed(1, 12).forEach(i -> monthStats.add(new MonthStats(i, (temp.get(i-1)))));
        return monthStats;
    }
}
