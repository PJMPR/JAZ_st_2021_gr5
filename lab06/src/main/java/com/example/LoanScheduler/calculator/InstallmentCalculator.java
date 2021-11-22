
package com.example.LoanScheduler.calculator;

import com.example.LoanScheduler.Loan.Installment;
import com.example.LoanScheduler.Loan.Timetable;
import com.example.LoanScheduler.Loan.installmentType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class InstallmentCalculator {

    public ArrayList<Installment> calculateInstalments(Timetable timetable) {
        ArrayList<Installment> installments = new ArrayList<>();

        double capital = 0;
        if (timetable.getInstallmentType() == installmentType.constant) {

            double totalInterest = (timetable.getPercentage() * timetable.getAmount()) + (timetable.getFixedFee() * timetable.getInstallmentCount());
            double monthlyInterest = totalInterest / timetable.getInstallmentCount();
            double capitalToPay = totalInterest + timetable.getAmount();
            double installment = capitalToPay / timetable.getInstallmentCount();

            for (int i = 0; i < timetable.getInstallmentCount(); i++) {
                installments.add(new Installment(timetable.getId(), i + 1, capital, monthlyInterest, timetable.getFixedFee(), capitalToPay, installment));
                capital += installment;
                capitalToPay -= installment;
            }
        } else if (timetable.getInstallmentType() == installmentType.decreasing) {
            double capitalToPay = timetable.getAmount();
            double percentage = timetable.getPercentage();
            long count = timetable.getInstallmentCount();
            double installment = timetable.getAmount() * percentage * Math.pow((percentage + 1), count) / (Math.pow((percentage + 1), count) - 1);
            double amount;

            for (int i = 0; i < timetable.getInstallmentCount(); i++) {
                double interest = capitalToPay * percentage;
                amount = installment + interest;
                installments.add(new Installment(timetable.getId(), i + 1, capital, interest, timetable.getFixedFee(), capitalToPay, amount));
                capital += amount;
                capitalToPay -= amount;
            }
        }

        return installments;
    }
}