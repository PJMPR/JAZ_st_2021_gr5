package com.lab06.demo.creditcalculators;

import com.lab06.demo.entities.Calculation;
import com.lab06.demo.entities.Timetable;
import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.List;

public class CreditConstant {

    public List<Timetable> constantRateCalculation(Calculation calculation){
        List<Timetable> timetableList = new ArrayList<>();
        int amount = calculation.getAmount();
        int installmentCount = calculation.getInstallmentCount();
        double percentage = calculation.getPercentage();
        double fixedRate = calculation.getFixedRate();
        double capitalStillToPay;
        double capitalAlreadyPaid;

        double baseAmountToPay = amount / installmentCount;
        double q = Precision.round(1 + (percentage/12), 5);
        double monthlyRate = Precision.round((amount*Precision.round(Math.pow(q,installmentCount),2)*(q-1)/Precision.round((Math.pow(q,installmentCount)-1),2)),2) + fixedRate;
        double interest = Precision.round((monthlyRate - baseAmountToPay - 30),2);

        for (int i = 1; i <= installmentCount; i++){
            capitalAlreadyPaid = baseAmountToPay * (i - 1);
            capitalStillToPay = amount - capitalAlreadyPaid;

            timetableList.add(i - 1, new Timetable(i, capitalAlreadyPaid, interest, fixedRate, capitalStillToPay, monthlyRate, calculation));
       }

        return timetableList;
    }
}
