package com.example.lab06;

import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Long>{
    public Loan findById(long id);
}
