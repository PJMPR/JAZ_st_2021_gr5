package com.example.lab06;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends CrudRepository<Installment, Integer>{
    public Installment findById(Integer id);
}
