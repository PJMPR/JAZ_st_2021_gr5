package com.example.LoanScheduler.Calculator;


import com.example.LoanScheduler.installment.Installment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InstallmentData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long amount;
    private long installmentCount;
    private installmentType installmentType;
    private double percentage;
    private long fixedFee;
//    private List<Installment> installments = new ArrayList<>();

    public InstallmentData(){};

    public InstallmentData(long amount, long installmentCount, installmentType installmentType, double percentage, long fixedFee) {
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.installmentType = installmentType;
        this.percentage = percentage;
        this.fixedFee = fixedFee;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(long installmentCount) {
        this.installmentCount = installmentCount;
    }

    public installmentType getInstallmentType() {
        return installmentType;
    }

    public void setInstallmentType(installmentType installmentType) {
        this.installmentType = installmentType;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public long getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(long fixedFee) {
        this.fixedFee = fixedFee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
