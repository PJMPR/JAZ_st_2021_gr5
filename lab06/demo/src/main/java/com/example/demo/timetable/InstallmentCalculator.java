package com.example.demo.timetable;

import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.List;

public class InstallmentCalculator {

    public List<Installment> calculateInstalments(Timetable t) {
        double capitalToPay, interest, amount, capital;
        List<Installment> installmentList = new ArrayList<>();
        for (int number = 1; number <= t.getInstallmentCount(); number++) {

            interest=calculateInterest(t,number);
            amount = t.getFixedFee() + interest;
            capitalToPay = t.getAmount() - t.getFixedFee() * number;
            capital = (t.getAmount() - capitalToPay) / t.getAmount();
            Installment newInstalment = new Installment(t.getId(), number, Precision.round(capital, 2), Precision.round(interest, 2), t.getFixedFee(), capitalToPay, Precision.round(amount, 2));
            installmentList.add(newInstalment);
        }
        return installmentList;
    }

    public double calculateInterest(Timetable t, int number) {
        double interest;
        switch (t.getInstalmentType()) {
            case "constant" -> interest = t.getFixedFee() * t.getPercentage();
            case "decreasing" -> interest = t.getFixedFee() * (t.getInstallmentCount() - number + 1) * t.getPercentage();
            default -> interest = 0;
        }
        return interest;
    }

}