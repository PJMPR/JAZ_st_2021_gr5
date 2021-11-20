package com.lab06.demo.calculation;

import com.lab06.demo.timetable.Timetable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Calculation {
    @Id
    @GeneratedValue(
           strategy = GenerationType.AUTO
    )
    private Long id;
    private int amount;
    private int installmentCount;

    @Enumerated(EnumType.STRING)
    private InstallmentType installmentType;

    private double percentage;
    private int fixedRate;

    @OneToMany(mappedBy = "calculation", cascade = CascadeType.ALL)
    private Set<Timetable> timetables = new HashSet<>();

    public Calculation(int amount, int installmentCount, InstallmentType installmentType, double percentage, int fixedRate) {
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.installmentType = installmentType;
        this.percentage = percentage;
        this.fixedRate = fixedRate;
    }

    public Calculation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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

    public int getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(int fixedRate) {
        this.fixedRate = fixedRate;
    }

    public Set<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(Set<Timetable> timetables) {
        this.timetables = timetables;

        for (Timetable t : timetables){
            t.setCalculation(this);
        }
    }
}
