package com.lab06.demo.creditcalculators;

import org.apache.commons.math3.util.Precision;

public class CreditDecreasing {
    public static void main(String[] args) {
        int amount = 300000;
        int installmentCount = 300;
        double percentage = 0.047;
        double fixedRate = 30;

        double baseAmountToPay = amount / installmentCount;

        for (int i = 1; i <= installmentCount; i++){
            double capitalAlreadyPaid = baseAmountToPay * (i - 1);
            double capitalStillToPay = amount - capitalAlreadyPaid;

            double Ro = Precision.round(((capitalStillToPay*percentage)/12) + fixedRate,2);

            System.out.println(i + "\t" + capitalAlreadyPaid + "\t" +  "\t" + fixedRate + "\t" + capitalStillToPay + "\t" + Ro);
        }

    }
}
