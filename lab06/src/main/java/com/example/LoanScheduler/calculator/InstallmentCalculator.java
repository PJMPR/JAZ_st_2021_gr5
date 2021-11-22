
package com.example.LoanScheduler.calculator;

import com.example.LoanScheduler.Loan.Installment;
import com.example.LoanScheduler.Loan.Timetable;
import com.example.LoanScheduler.Loan.installmentType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstallmentCalculator {

//    public List<Installment> calculateInstalments(Timetable t) {
//        double capitalToPay, interest, amount, capital;
//        List<Installment> installmentList = new ArrayList<>();
//        for (int number = 1; number <= t.getInstallmentCount(); number++) {
//
//            interest=calculateInterest(t,number);
//            amount = t.getFixedFee() + interest;
//            capitalToPay = t.getAmount() - t.getFixedFee() * number;
//            capital = (t.getAmount() - capitalToPay);
//            Installment newInstalment = new Installment(t.getId(), number, capital, interest, t.getFixedFee(), capitalToPay, amount);
//            installmentList.add(newInstalment);
//        }
//        return installmentList;
//    }


//    private double calculateInterest(Timetable t, int number) {
//        double interest;
//        switch (t.getInstallmentType()) {
//            case constant -> interest = t.getFixedFee() * t.getPercentage();
//            case decreasing -> interest = t.getFixedFee() * (t.getInstallmentCount() - number + 1) * t.getPercentage();
//            default -> interest = 0;
//        }
//        return interest;
//    }

    public ArrayList<Installment> calculateInstalments(Timetable timetable) {
        ArrayList<Installment> installments = new ArrayList<>();

        double capital = 0;
        if(timetable.getInstallmentType() == installmentType.constant) {

            double totalInterest = (timetable.getPercentage() * timetable.getAmount()) + (timetable.getFixedFee() * timetable.getInstallmentCount());
            double monthlyInterest = totalInterest / timetable.getInstallmentCount();
            double capitalToPay = totalInterest + timetable.getAmount();
            double installment = capitalToPay / timetable.getInstallmentCount();

            for (int i = 0; i < timetable.getInstallmentCount(); i++) {
                installments.add(new Installment(timetable.getId(), i+1, capital, monthlyInterest, timetable.getFixedFee(), capitalToPay, installment));
                capital += installment;
                capitalToPay -= installment;
            }
        } else if(timetable.getInstallmentType() == installmentType.decreasing){
            double capitalToPay = timetable.getAmount();
            double percentage = timetable.getPercentage();
            long count = timetable.getInstallmentCount();
            double installment = timetable.getAmount() * percentage * Math.pow((percentage+1), count) / (Math.pow((percentage+1), count)-1);
            double amount;

            for (int i = 0; i < timetable.getInstallmentCount(); i++) {
                double interest = capitalToPay * percentage;
                amount = installment + interest;
                installments.add(new Installment(timetable.getId(), i+1, capital, interest, timetable.getFixedFee(), capitalToPay, amount));
                capital += amount;
                capitalToPay -= amount;
            }
        }

        return installments;
    }
}