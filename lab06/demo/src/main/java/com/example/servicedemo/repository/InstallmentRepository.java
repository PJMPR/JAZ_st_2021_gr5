package com.example.servicedemo.repository;

import com.example.servicedemo.Timetable.Installment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends CrudRepository<Installment, Integer> {
    Installment findById(int id);
}
