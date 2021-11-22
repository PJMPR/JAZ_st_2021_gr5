package com.example.LoanScheduler.calculator;

import com.example.LoanScheduler.Loan.Installment;
import com.example.LoanScheduler.Loan.Timetable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstallmentCalculator {

    public List<Installment> calculateInstalments(Timetable t) {
        double capitalToPay, interest, amount, capital;
        List<Installment> installmentList = new ArrayList<>();
        for (int number = 1; number <= t.getInstallmentCount(); number++) {

            interest=calculateInterest(t,number);
            amount = t.getFixedFee() + interest;
            capitalToPay = t.getAmount() - t.getFixedFee() * number;
            capital = (t.getAmount() - capitalToPay);
            Installment newInstalment = new Installment(t.getId(), number, capital, interest, t.getFixedFee(), capitalToPay, amount);
            installmentList.add(newInstalment);
        }
        return installmentList;
    }


    private double calculateInterest(Timetable t, int number) {
        double interest;
        switch (t.getInstallmentType()) {
            case constant -> interest = t.getFixedFee() * t.getPercentage();
            case decreasing -> interest = t.getFixedFee() * (t.getInstallmentCount() - number + 1) * t.getPercentage();
            default -> interest = 0;
        }
        return interest;
    }
}
