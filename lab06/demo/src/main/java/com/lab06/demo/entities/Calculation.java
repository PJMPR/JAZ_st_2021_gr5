package com.lab06.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Calculation {
    @Id
    @SequenceGenerator(
            name = "calculation_sequence",
            sequenceName = "calculation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
            generator = "calculation_sequence"
    )
    private Long id;
    private int amount;
    private int installmentCount;

    @Enumerated(EnumType.STRING)
    private InstallmentType installmentType;

    private double percentage;
    private int fixedRate;

    @OneToMany(mappedBy = "calculation", cascade = CascadeType.ALL, orphanRemoval = true)
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
}
