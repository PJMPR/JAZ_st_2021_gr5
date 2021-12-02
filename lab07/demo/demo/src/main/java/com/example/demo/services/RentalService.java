package com.example.demo.services;

import com.example.demo.data.MonthStats;
import com.example.demo.data.Rental;
import com.example.demo.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
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

    public int getIncomeByMonth(String from, String to) {
        Timestamp timeFrom = Timestamp.valueOf(from+" 00:00:01");
        Timestamp timeTo = Timestamp.valueOf(to+" 23:59:59");
        return rentalRepository.findAll().stream()
                .map(Rental::getPaymentsByRentalId)
                .map(x -> x.stream().filter(p -> p.getPaymentDate().after(timeFrom) && p.getPaymentDate().before(timeTo)))
                .map(x -> x.map(p -> p.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add).intValue())
                .reduce(0, Integer::sum);
    }

    public List<MonthStats> getIncomeByYear(int year) {
        ArrayList<MonthStats> monthStats = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        IntStream.rangeClosed(1, 12).forEach(i -> temp.add(getIncomeByMonth(year+"-"+i+"-01", year+"-"+i+"-31")));
        IntStream.rangeClosed(1, 12).forEach(i -> monthStats.add(new MonthStats(i, (temp.get(i-1)))));
        return monthStats;
    }

    public Object gerIncomeFromTo(String from, String to) {
        ArrayList<MonthStats> monthStats = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        int months = Period.between(LocalDate.parse(from).withDayOfMonth(1), LocalDate.parse(to).withDayOfMonth(1)).getMonths();
        Calendar calendar = Calendar.getInstance();

        IntStream.rangeClosed(1, months).forEach(i -> temp.add(getIncomeByMonth(from, to)));
        IntStream.rangeClosed(1, months).forEach(i -> monthStats.add(new MonthStats(i, (temp.get(i-1)))));
        return monthStats;
    }
}
