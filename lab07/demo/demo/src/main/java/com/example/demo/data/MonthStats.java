package com.example.demo.data;

public class MonthStats {
    private String month;
    private int value;

    public MonthStats(String month, int value) {
        this.month = month;
        this.value = value;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}