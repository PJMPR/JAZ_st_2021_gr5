package com.lab06.demo.creditcalculators;

import com.lab06.demo.entities.Calculation;
import com.lab06.demo.entities.Timetable;
import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.List;

public class CreditDecreasing {
    public List<Timetable> decreasingRateCalculation(Calculation calculation) {
        List<Timetable> timetableList = new ArrayList<>();

        int amount = calculation.getAmount();
        int installmentCount = calculation.getInstallmentCount();
        double percentage = calculation.getPercentage();
        double fixedRate = calculation.getFixedRate();
        double capitalStillToPay;
        double capitalAlreadyPaid;
        double interest;
        double monthlyRate;

        double baseAmountToPay = amount / installmentCount;

        for (int i = 1; i <= installmentCount; i++){
            capitalAlreadyPaid = baseAmountToPay * (i - 1);
            capitalStillToPay = amount - capitalAlreadyPaid;

            monthlyRate = Precision.round(((capitalStillToPay*percentage)/12) + fixedRate,2);
            interest = Precision.round((monthlyRate - 30),2);

            timetableList.add(i - 1, new Timetable(i, capitalAlreadyPaid, interest, fixedRate, capitalStillToPay, monthlyRate, calculation));
        }
        return timetableList;
    }
}
