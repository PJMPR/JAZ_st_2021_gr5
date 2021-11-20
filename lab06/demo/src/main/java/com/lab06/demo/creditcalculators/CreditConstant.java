package com.lab06.demo.creditcalculators;

import org.apache.commons.math3.util.Precision;

public class CreditConstant {
    public static void main(String[] args) {

        int amount = 300000;
        int installmentCount = 300;
        double percentage = 0.047;
        double fixedRate = 30;

        double baseAmountToPay = amount / installmentCount;
        double q = Precision.round(1 + (percentage/12), 5);
        double monthlyRate = Precision.round((amount*Precision.round(Math.pow(q,installmentCount),2)*(q-1)/Precision.round((Math.pow(q,installmentCount)-1),2)),2) + fixedRate;
        double interest = Precision.round((monthlyRate - baseAmountToPay - 30),2);

        for (int i = 1; i <= installmentCount; i++){
            double capitalAlreadyPaid = baseAmountToPay * (i - 1);
            double capitalStillToPay = amount - capitalAlreadyPaid;

            System.out.println(i + "\t" + capitalAlreadyPaid + "\t" + interest + "\t" + fixedRate + "\t" + capitalStillToPay + "\t" + monthlyRate);
        }
    }


}
