package com.example.servicedemo.Timetable;

import javax.persistence.*;

@Entity
@Table(name = "Installment")
public class Installment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int timetable_id;
    private int number;
    private double capital;
    private double interest;
    private double fixedFee;
    private double capitalToPay;
    private double amount;

    public Installment(){}

    public Installment(int id,int number, double capital, double interest, double fixedFee, double capitalToPay, double amount) {
        this.timetable_id = id;
        this.number = number;
        this.capital = capital;
        this.interest = interest;
        this.fixedFee = fixedFee;
        this.capitalToPay = capitalToPay;
        this.amount = amount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(long capital) {
        this.capital = capital;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(long fixedFee) {
        this.fixedFee = fixedFee;
    }

    public double getCapitalToPay() {
        return capitalToPay;
    }

    public void setCapitalToPay(long capitalToPay) {
        this.capitalToPay = capitalToPay;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
