package com.example.demo.timetable;

import com.example.demo.creditParameters.CreditParameters;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TIMETABLE")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int amount;
    private int installmentCount;
    private double percentage;
    private int fixedFee;
    private String instalmentType;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "timetable_id")
    private List<Installment> instalments = new ArrayList<>();


    public Timetable() {
    }

    public Timetable(CreditParameters creditParameters) {
        this.amount = creditParameters.amount();
        this.installmentCount = creditParameters.installmentCount();
        this.percentage = creditParameters.percentage();
        this.fixedFee = creditParameters.fixedFee();
        this.instalmentType = creditParameters.installmentType();
        this.instalments = new ArrayList<>();
    }

    public Timetable(int id, int amount, int installmentCount, double percentage, int fixedFee, String instalmentType) {
        this.id = id;
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.percentage = percentage;
        this.fixedFee = fixedFee;
        this.instalmentType = instalmentType;
        this.instalments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public int getInstallmentCount() {
        return installmentCount;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getFixedFee() {
        return fixedFee;
    }

    public String getInstalmentType() {
        return instalmentType;
    }

    public List<Installment> getInstalments() {
        return instalments;
    }
}
