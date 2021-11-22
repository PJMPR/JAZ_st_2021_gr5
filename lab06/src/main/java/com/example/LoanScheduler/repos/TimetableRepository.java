package com.example.LoanScheduler.repos;

import com.example.LoanScheduler.Loan.Installment;
import com.example.LoanScheduler.Loan.Timetable;
import org.springframework.data.repository.CrudRepository;

public interface TimetableRepository extends CrudRepository<Timetable,Integer> {
    Timetable findById(int id);
}
