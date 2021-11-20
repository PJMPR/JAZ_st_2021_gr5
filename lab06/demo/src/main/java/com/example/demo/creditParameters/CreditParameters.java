package com.example.demo.creditParameters;


//public class CreditParameters {
//    private int amount;
//    private int instalmentCount;
//    private  InstalmentType instalmentType;
//    private double percentage;
//    private  int fixedFee;
//}

public record CreditParameters(int amount,int instalmentCount, InstalmentType instalmentType,double percentage,int fixedFee) {
}