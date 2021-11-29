package com.example.demo.model;

import com.example.demo.data.Customer;

import java.math.BigDecimal;

public class customerSpentMoney {

    private int customerId;
    private String firstName;
    private String lastName;
    private BigDecimal moneySpent;

    public customerSpentMoney(int customerId, String firstName, String lastName, BigDecimal moneySpent) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.moneySpent = moneySpent;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(BigDecimal moneySpent) {
        this.moneySpent = moneySpent;
    }
}
