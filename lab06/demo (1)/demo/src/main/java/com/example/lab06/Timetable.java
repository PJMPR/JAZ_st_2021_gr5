package com.example.lab06;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private double amount;
    private int installmentCount;
    private InstallmentType type;
    private double percentage;
    private double fixedRate;

    public Timetable() {}

    public Timetable(double amount, int installmentCount, InstallmentType type, double percentage, double fixedRate) {
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.type = type;
        this.percentage = percentage;
        this.fixedRate = fixedRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public InstallmentType getType() {
        return type;
    }

    public void setType(InstallmentType type) {
        this.type = type;
    }

    public double getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(double fixedRate) {
        this.fixedRate = fixedRate;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
