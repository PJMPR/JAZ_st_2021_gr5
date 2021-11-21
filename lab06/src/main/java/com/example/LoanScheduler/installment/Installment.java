package com.example.LoanScheduler.installment;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Installment {
    @Id
    private Long id;
    private int number;
    private long capital;
    private double interest;
    private long fixedFee;
    private long capitalToPay;
    private double amount;


    public Installment(){};


    public Installment(Long id,int number, long capital, double interest, long fixedFee, long capitalToPay, double amount) {
        this.id = id;
        this.number = number;
        this.capital = capital;
        this.interest = interest;
        this.fixedFee = fixedFee;
        this.capitalToPay = capitalToPay;
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
