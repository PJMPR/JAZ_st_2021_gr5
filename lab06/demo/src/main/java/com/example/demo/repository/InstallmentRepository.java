package com.example.demo.repository;

import com.example.demo.timetable.Installment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstallmentRepository extends CrudRepository<Installment,Integer>{
//    public Installment findById(int id);
//    public List<Installment> findAllBytimetable_id(int id);
}
