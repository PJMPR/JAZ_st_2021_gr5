package com.example.LoanScheduler.repos;

import com.example.LoanScheduler.installment.Installment;
import org.springframework.data.repository.CrudRepository;

public interface TimetableRepository extends CrudRepository<Installment,Long> {
    public Installment findById(long id);
}
