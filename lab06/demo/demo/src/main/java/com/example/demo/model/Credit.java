package com.example.demo.model;

import com.example.demo.contract.InstallmentType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private int installmentCount;
    private double percentage;
    private double fixedFee;

    @OneToMany(mappedBy = "credit", fetch = FetchType.EAGER)
    private List<TimetablePosition> timetable = new ArrayList<TimetablePosition>();

    private InstallmentType installmentType;

    public InstallmentType getInstallmentType() {
        return installmentType;
    }

    public void setInstallmentType(InstallmentType installmentType) {
        this.installmentType = installmentType;
    }

    public List<TimetablePosition> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<TimetablePosition> timetable) {
        this.timetable = timetable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(double fixedFee) {
        this.fixedFee = fixedFee;
    }
}
