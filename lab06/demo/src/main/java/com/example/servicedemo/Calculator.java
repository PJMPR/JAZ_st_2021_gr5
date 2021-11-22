package com.example.servicedemo;

import com.example.servicedemo.Timetable.Installment;
import com.example.servicedemo.Timetable.Timetable;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public Calculator() {
    }

    public List<Installment> calculate(Timetable t) {
        double capitalToPay;
        double interest;
        double amount;
        double capital;
        List<Installment> installmentList = new ArrayList<>();


        for (int number = 1; number <= t.getInstallmentCount(); number++) {
            interest=calculateInterest(t,number);
            amount = t.getFixedRate() + interest;
            capitalToPay = t.getAmount() - t.getFixedRate() * number;
            capital = (t.getAmount() - capitalToPay);
            Installment newInstalment = new Installment(t.getID(), number, capital, interest, t.getFixedRate(), capitalToPay, amount);
            installmentList.add(newInstalment);
        }
        return installmentList;
    }


    public static double calculateInterest(Timetable t, int number) {
        double interest;
        switch (t.getInstallmentType()) {
            case CONSTANT -> interest = t.getFixedRate() * t.getPercentage();
            case DECREASING -> interest = t.getFixedRate() * (t.getInstallmentCount() - number + 1) * t.getPercentage();
            default -> interest = 0;
        }
        return interest;
    }
}
