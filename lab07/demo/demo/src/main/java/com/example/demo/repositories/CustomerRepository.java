package com.example.demo.repositories;

import com.example.demo.data.Customer;
import com.example.demo.projections.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Query(value = "select sum(payment.amount) as 'spent', customer.customer_id as 'id', customer.first_name as 'firstName', customer.last_name as 'lastName' " +
            "from payment left join customer on payment.customer_id = customer.customer_id " +
            "group by customer.customer_id order by spent desc limit 10", nativeQuery = true)
    List<IFindTop10ThatSpentMost> findTop10BySpentMost();


    @Query(value = "select count(payment.amount) as 'watched', customer.customer_id as 'id', customer.first_name as 'firstName', customer.last_name as 'lastName' " +
            "from payment left join customer on payment.customer_id = customer.customer_id " +
            "group by customer.customer_id order by watched desc limit 10", nativeQuery = true)
    List<IFindTop10ByWatchedMost> findTop10ByWatchedMost();

    @Query(value = "select month(rental_date) as 'month', count(*) as 'rentmovies' from rental " +
            "where year(rental_date) = :year group by month", nativeQuery = true)
    List<IFindRentMoviesByMonth> findRentMoviesByMonth(String year);

    @Query(value = "select year(payment_date) as 'year', month(payment_date) as 'month', count(*) as 'rentmovies' from payment " +
            "where customer_id = :customerid group by month order by year", nativeQuery = true)
    List<IFindRentMoviesByMonthByCustomer> findRentMoviesByMonthByCustomer(String customerid);
}
