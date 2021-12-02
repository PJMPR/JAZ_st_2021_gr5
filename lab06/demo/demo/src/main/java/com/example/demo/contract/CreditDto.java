package com.example.demo.contract;

import com.example.demo.model.TimetablePosition;

import java.util.ArrayList;
import java.util.List;

public class CreditDto {

    private double amount;
    private int installmentCount;
    private double percentage;
    private double fixedFee;

    private InstallmentType installmentType;
    public InstallmentType getInstallmentType() {
        return installmentType;
    }

    public void setInstallmentType(InstallmentType installmentType) {
        this.installmentType = installmentType;
    }


    private List<TimetablePositionDto> timetable = new ArrayList<TimetablePositionDto>();

    public List<TimetablePositionDto> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<TimetablePositionDto> timetable) {
        this.timetable = timetable;
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
