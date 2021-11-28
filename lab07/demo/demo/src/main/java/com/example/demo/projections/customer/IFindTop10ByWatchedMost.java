package com.example.demo.projections.customer;

import org.springframework.beans.factory.annotation.Value;

public interface IFindTop10ByWatchedMost {
    int getWatched();

    @Value("#{new com.example.demo.projections.customer.CustomerInfo(target.id, target.firstName, target.lastName)}")
    CustomerInfo getCustomer();
}
