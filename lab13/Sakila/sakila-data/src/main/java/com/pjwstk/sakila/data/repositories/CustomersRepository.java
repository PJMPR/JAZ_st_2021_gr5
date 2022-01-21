package com.pjwstk.sakila.data.repositories;

import com.pjwstk.sakila.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customer, Long> {

    @Query( value = "" +
            "SELECT * " +
            "FROM customer " +
            "JOIN payment on customer.customer_id=payment.customer_id " +
            "GROUP BY customer.customer_id " +
            "ORDER BY SUM(payment.amount) DESC " +
            "LIMIT :limit", nativeQuery = true)
    public List<Customer> customersThatSpentMoney(int limit);

    @Query( value = "" +
            "SELECT * " +
            "FROM customer " +
            "JOIN rental on customer.customer_id=rental.customer_id " +
            "GROUP BY customer.customer_id " +
            "ORDER BY COUNT(rental.rental_id) DESC " +
            "LIMIT :limit", nativeQuery = true)
    public List<Customer> customersWithMostRentals(int limit);
}
