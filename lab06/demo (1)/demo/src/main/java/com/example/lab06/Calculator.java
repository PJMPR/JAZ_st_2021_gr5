package com.example.lab06;
import java.util.ArrayList;

public class Calculator {
    public ArrayList<Installment> calc(Timetable timetable) {
        ArrayList<Installment> listOfInstallments = new ArrayList<>();

        double capital = 0;
        if(timetable.getType() == InstallmentType.constant) {

            double totalInterest = (timetable.getPercentage() * timetable.getAmount()) + (timetable.getFixedRate() * timetable.getInstallmentCount());
            double monthlyInterest = totalInterest / timetable.getInstallmentCount();
            double capitalToPay = totalInterest + timetable.getAmount();
            double installment = capitalToPay / timetable.getInstallmentCount();

            for (int i = 0; i < timetable.getInstallmentCount(); i++) {
                listOfInstallments.add(new Installment(timetable.getId(), i, capital, monthlyInterest, timetable.getFixedRate(), capitalToPay, installment));
                capital += installment;
                capitalToPay -= installment;
            }
        } else {
            double capitalToPay = timetable.getAmount();
            double percentage = timetable.getPercentage();
            int count = timetable.getInstallmentCount();
            double installment = timetable.getAmount() * percentage * Math.pow((percentage+1), count) / (Math.pow((percentage+1), count)-1);
            double amount;

            for (int i = 0; i < timetable.getInstallmentCount(); i++) {
                double interest = capitalToPay * percentage;
                amount = installment - interest;
                listOfInstallments.add(new Installment(timetable.getId(), i, capital, interest, timetable.getFixedRate(), capitalToPay, amount));
                capital += amount;
                capitalToPay -= amount;
            }
        }

        return listOfInstallments;
    }
}
