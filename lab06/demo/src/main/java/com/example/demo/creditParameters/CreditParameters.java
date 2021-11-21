package com.example.demo.creditParameters;

public record CreditParameters(int amount, int installmentCount, String installmentType, double percentage, int fixedFee) {
}