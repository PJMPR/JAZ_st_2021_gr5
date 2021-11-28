package com.example.demo.projections;

import java.math.BigDecimal;

public interface IFindTop10ThatSpentMost {
    BigDecimal getSpent();

    //TODO:try to make nested interface working
    int getId();

    String getFirstName();

    String getLastName();
}


