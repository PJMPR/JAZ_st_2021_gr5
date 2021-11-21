package com.lab06.demo.creditcalculators;

import com.lab06.demo.entities.Calculation;
import com.lab06.demo.entities.Timetable;
import org.apache.commons.math3.util.Precision;

import java.util.List;

public class CreditDecreasing extends Credit{
    private Calculation calculation;

    public CreditDecreasing(Calculation calculation) {
        super(calculation.getAmount(), calculation.getInstallmentCount(), calculation.getPercentage(), calculation.getFixedRate());
        this.calculation = calculation;
    }

    public List<Timetable> decreasingRateCalculation() {
        double baseAmountToPay = amount / installmentCount;
        double monthlyRate;
        double interest;

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
