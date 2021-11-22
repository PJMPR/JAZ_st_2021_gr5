package com.example.lab06;

import com.example.lab06.Type.InstallmentType;

import java.util.ArrayList;

public class InstallmentCalculator {

    public ArrayList<Installment> calculate(Timetable timetable) {
        ArrayList<Installment> installments = new ArrayList<>();

        double capital = 0;
        if(timetable.getInstallmentType() == InstallmentType.CONSTANT){
            double interestTotal = (timetable.getPercentage() * timetable.getAmount()) + (timetable.getFixedRate() * timetable.getInstallmentCount());
            double interestMonthly = interestTotal / timetable.getInstallmentCount();
            double capitalToPay = interestTotal + timetable.getAmount();
            double emi = capitalToPay / timetable.getInstallmentCount();
            for (int i = 1; i <= timetable.getInstallmentCount(); i++) {
                installments.add(new Installment(timetable.getId(), (long) i, capital, interestMonthly, timetable.getFixedRate(), capitalToPay, emi));
                capital += emi;
                capitalToPay -= emi;
            }
        }else if(timetable.getInstallmentType() == InstallmentType.DECREASING){
            double capitalToPay = timetable.getAmount();
            double r = timetable.getPercentage();
            int c = timetable.getInstallmentCount();
            double emi = timetable.getAmount() * r * Math.pow((r + 1), c) / (Math.pow((r + 1), c) - 1);
            double amount;
            for (int i = 1; i <= timetable.getInstallmentCount(); i++) {
                double interest = capitalToPay * timetable.getPercentage();
                amount = emi - interest;
                installments.add(new Installment(timetable.getId(), (long) i, capital, interest, timetable.getFixedRate(), capitalToPay, amount));
                capital += amount;
                capitalToPay -= amount;
            }
        }
        return installments;
    }
}
