package com.example.demo.repositories;

import com.example.demo.data.Payment;
import com.example.demo.projections.rental.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Payment, Integer> {

    @Query(value = "select month(payment_date) as 'month', sum(amount) as 'income' from payment " +
            "where year(payment_date) = :year group by month", nativeQuery = true)
    List<IFindIncome> incomeByMonth(String year);

    @Query(value = "select cast(date_format(date(payment_date), '%Y-%m-01') as date) as 'month', sum(amount) as 'income' " +
            "from payment where payment_date between :from and :to " +
            "group by month(payment_date) order by year(payment_date)", nativeQuery = true)
    List<IFindIncome> incomeByMonthFromTo(String from, String to);
}
