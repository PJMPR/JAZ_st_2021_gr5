package com.example.lab06.Repository;

import com.example.lab06.Installment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends CrudRepository<Installment, Long>{
    public Installment findById(long id);
}
