package com.example.demo;

import com.example.servicedemo.Calculator;
import com.example.servicedemo.Timetable.InstallmentType;
import com.example.servicedemo.Timetable.Timetable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTests {
    @Test
    public void timetableValuesShouldBe0(){
        Timetable timetable = new Timetable(0,0,0,InstallmentType.CONSTANT,0.00,0.00);
        Assertions.assertEquals(0,timetable.getAmount());
        Assertions.assertEquals(0,timetable.getInstallmentCount());
        Assertions.assertEquals(0,timetable.getPercentage());
        Assertions.assertEquals(0,timetable.getFixedRate());
    }

    @Test
    public void timetableInstallmentShouldBeConstant(){
        Timetable timetable = new Timetable(0,0,0,InstallmentType.CONSTANT,0.00,0.00);
        Assertions.assertEquals(InstallmentType.CONSTANT,timetable.getInstallmentType());
    }

    @Test
    public void testCalculateInterestForTypeConstant() {
        Timetable timetable = new Timetable(1, 100, 5, InstallmentType.CONSTANT, 20, 0.1);
        double interest1 = Calculator.calculateInterest(timetable, 1);
        double interest2 = Calculator.calculateInterest(timetable, 2);
        double interest3 = Calculator.calculateInterest(timetable, 3);
        double interest4 = Calculator.calculateInterest(timetable, 5);
        Assertions.assertEquals(2, interest1, 0.1);
        Assertions.assertEquals(2, interest2, 0.1);
        Assertions.assertEquals(2, interest3, 0.1);
        Assertions.assertEquals(2, interest4, 0.1);
    }

    @Test
    public void testCalculateInterestForTypeDecreasing() {
        Timetable timetable = new Timetable(1, 200, 20, InstallmentType.DECREASING, 10, 0.1);
        double interest1 = Calculator.calculateInterest(timetable, 1);
        double interest2 = Calculator.calculateInterest(timetable, 2);
        double interest3 = Calculator.calculateInterest(timetable, 7);
        double interest4 = Calculator.calculateInterest(timetable, 20);
        Assertions.assertEquals(20, interest1, 0.1);
        Assertions.assertEquals(19, interest2, 0.1);
        Assertions.assertEquals(14, interest3, 0.1);
        Assertions.assertEquals(1, interest4, 0.1);
    }

}
