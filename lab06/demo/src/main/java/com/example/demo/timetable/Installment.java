package com.example.demo.timetable;

import javax.persistence.*;

@Entity
@Table(name = "INSTALLMENT")
public class Installment {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private int timetable_id;
    private int number;
    private double capital;
    private double interest;
    private int fixedFee;
    private double capitalToPay;
    private double amount;

    public Installment() {
    }

    public Installment(int timetable_id, int number, double capital, double interest, int fixedFee, double capitalToPay, double amount) {
        this.timetable_id = timetable_id;
        this.number = number;
        this.capital = capital;
        this.interest = interest;
        this.fixedFee = fixedFee;
        this.capitalToPay = capitalToPay;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getTimetable_id() {
        return timetable_id;
    }

    public int getNumber() {
        return number;
    }

    public double getCapital() {
        return capital;
    }

    public double getInterest() {
        return interest;
    }

    public int getFixedFee() {
        return fixedFee;
    }

    public double getCapitalToPay() {
        return capitalToPay;
    }

    public double getAmount() {
        return amount;
    }
}
