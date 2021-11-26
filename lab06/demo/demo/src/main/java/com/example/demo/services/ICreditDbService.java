package com.example.demo.services;

import com.example.demo.contract.CreditDto;

public interface ICreditDbService {
    int saveTimetable(CreditDto creditParameters);
    CreditDto getCredit(int id);
}
