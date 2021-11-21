package com.example.lab06;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class LoanService {
    LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository){
        loanRepository.save(new Loan(1000, 5, InstallmentType.CONSTANT, 0.03, 30));
        loanRepository.save(new Loan(100000, 50, InstallmentType.DECREASING, 0.03, 30));
        this.loanRepository = loanRepository;
    }

    public long save(Loan loan) {
        return loanRepository.save(loan).getId();
    }

    public Loan getLoan(long id) {
        return  loanRepository.findById(id);
    }

    public File getLoanFile(long id, FileType file) {
        Loan loan = getLoan(id);
        return FileConverter.convert(file, loan);
    }
}
