package com.example.demo.projections;

import java.math.BigDecimal;

public interface IFindTop10ThatSpentMost {
    BigDecimal getSpent();

    int getId();

    String getFirstName();

    String getLastName();
//    TODO:Make nested interfaces work!
//    List<CustomerInfo> getCustomer();
}

interface CustomerInfo {
    int getId();

    String getFirstName();

    String getLastName();
}


