package com.example.LoanScheduler.repos;

import com.example.LoanScheduler.Calculator.InstallmentData;
import org.springframework.data.repository.CrudRepository;

public interface InstallmentRepository extends CrudRepository<InstallmentData,Long>{
    public InstallmentData findById(long id);
}
