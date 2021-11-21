package com.example.lab06;

public record Installment(Long LoanId, Long number, double capital, double interest, double fixeFee, double capitalToPay, double amount){}
