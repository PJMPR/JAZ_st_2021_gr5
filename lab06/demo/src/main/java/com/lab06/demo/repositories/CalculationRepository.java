package com.lab06.demo.repositories;

import com.lab06.demo.entities.Calculation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends CrudRepository<Calculation, Long> {
    Calculation findById(long id);
}
