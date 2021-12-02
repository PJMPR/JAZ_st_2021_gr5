package com.example.demo.data;

public class MonthStats {
    private int month;
    private int value;

    public MonthStats(int month, int value){
        this.month = month;
        this.value = value;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
