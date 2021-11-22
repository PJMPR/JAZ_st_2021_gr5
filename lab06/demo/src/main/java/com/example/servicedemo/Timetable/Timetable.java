package com.example.servicedemo.Timetable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private double amount;
    private int installmentCount;
    private InstallmentType installmentType;
    private double percentage;
    private double fixedRate;
    @OneToMany
    @JoinColumn(name="timetable_id")
    private List<Installment> installments = new ArrayList<>();

    public Timetable(){

    }

    public Timetable(int ID, double amount, int installmentCount, InstallmentType installmentType, double percentage, double fixedRate) {
        this.ID = ID;
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.installmentType = installmentType;
        this.percentage = percentage;
        this.fixedRate = fixedRate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }
}
