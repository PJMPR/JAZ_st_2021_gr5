package com.lab06.demo.creditcalculators;

import com.lab06.demo.entities.Calculation;
import com.lab06.demo.entities.Timetable;
import org.apache.commons.math3.util.Precision;

import java.util.List;

public class CreditConstant extends Credit{
    private Calculation calculation;

    public CreditConstant(Calculation calculation) {
        super(calculation.getAmount(), calculation.getInstallmentCount(), calculation.getPercentage(), calculation.getFixedRate());
        this.calculation = calculation;
    }

    public List<Timetable> constantRateCalculation(){
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
