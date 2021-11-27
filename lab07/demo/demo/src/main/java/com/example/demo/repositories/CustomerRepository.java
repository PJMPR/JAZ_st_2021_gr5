package com.example.demo.repositories;

import com.example.demo.data.Customer;
import com.example.demo.projections.IFindTop10ByWatchedMost;
import com.example.demo.projections.IFindTop10ThatSpentMost;
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
}
