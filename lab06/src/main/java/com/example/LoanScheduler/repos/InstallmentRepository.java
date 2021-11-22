package com.example.LoanScheduler.repos;

import com.example.LoanScheduler.Loan.Installment;
import com.example.LoanScheduler.Loan.Timetable;
import org.springframework.data.repository.CrudRepository;

public interface InstallmentRepository extends CrudRepository<Installment, Integer>{
    Installment findById(int id);
}
