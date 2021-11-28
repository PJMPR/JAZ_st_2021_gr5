package com.example.demo.projections.customer;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface IFindTop10ThatSpentMost {
    BigDecimal getSpent();

    @Value("#{new com.example.demo.projections.customer.CustomerInfo(target.id, target.firstName, target.lastName)}")
    CustomerInfo getCustomer();
}


