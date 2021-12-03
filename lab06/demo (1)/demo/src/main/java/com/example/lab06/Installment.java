package com.example.lab06;

public record Installment(Integer id, Integer number, double capital, double interest, double fixedFee, double capitalToPay, double amount) {
}
