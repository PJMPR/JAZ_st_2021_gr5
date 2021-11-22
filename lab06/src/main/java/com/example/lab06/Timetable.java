package com.example.lab06;

import com.example.lab06.Type.InstallmentType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double amount;
    private int installmentCount;
    private InstallmentType installmentType;
    private double percentage;
    private double fixedRate;

    public Timetable(){}

    public Timetable(double amount, int installmentCount, InstallmentType installmentType, double percentage, double fixedRate) {
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.installmentType = installmentType;
        this.percentage = percentage;
        this.fixedRate = fixedRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(int installmentCount) {
        this.installmentCount = installmentCount;
    }

    public InstallmentType getInstallmentType() {
        return installmentType;
    }

    public void setInstallmentType(InstallmentType installmentType) {
        this.installmentType = installmentType;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(double fixedRate) {
        this.fixedRate = fixedRate;
    }
}
