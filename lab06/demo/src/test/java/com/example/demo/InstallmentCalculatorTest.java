package com.example.demo;

import com.example.demo.timetable.Installment;
import com.example.demo.timetable.InstallmentCalculator;
import com.example.demo.timetable.Timetable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InstallmentCalculatorTest {
    InstallmentCalculator installmentCalculator = new InstallmentCalculator();

    @Test
    public void testIfCalculateInstalmentsReturnsEmptyListForTimetableWithInstallmentCountEqual0() {
        Timetable timetable = new Timetable(1, 100, 0, 0.1, 100, "constant");
        List<Installment> result = installmentCalculator.calculateInstalments(timetable);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testIfCalculateInstalmentsReturnsTimetableWith3elements() {
        Timetable timetable = new Timetable(1, 100, 3, 0.2, 34, "decreasing");
        List<Installment> result = installmentCalculator.calculateInstalments(timetable);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals(54.4, result.get(0).getAmount());
        Assertions.assertEquals(47.6, result.get(1).getAmount());
        Assertions.assertEquals(40.8, result.get(2).getAmount());
    }

    @Test
    public void testIfCalculateInstalmentsWithConstantTypeReturnsCorrectTimetable() {
        Timetable timetable = new Timetable(1, 150, 5, 0.2, 30, "constant");
        List<Installment> result = installmentCalculator.calculateInstalments(timetable);
        Assertions.assertEquals(5, result.size());
        Assertions.assertEquals(36, result.get(0).getAmount());
        Assertions.assertEquals(36, result.get(1).getAmount());
        Assertions.assertEquals(36, result.get(4).getAmount());
    }

    @Test
    public void testCalculateInterestForTypeConstant() {
        Timetable timetable = new Timetable(1, 100, 5, 0.1, 20, "constant");
        double interest1 = installmentCalculator.calculateInterest(timetable, 1);
        double interest2 = installmentCalculator.calculateInterest(timetable, 2);
        double interest3 = installmentCalculator.calculateInterest(timetable, 3);
        double interest4 = installmentCalculator.calculateInterest(timetable, 5);
        Assertions.assertEquals(2d, interest1);
        Assertions.assertEquals(2d, interest2);
        Assertions.assertEquals(2d, interest3);
        Assertions.assertEquals(2d, interest4);
    }

    @Test
    public void testCalculateInterestForTypeDecreasing() {
        Timetable timetable = new Timetable(1, 200, 20, 0.1, 10, "decreasing");
        double interest1 = installmentCalculator.calculateInterest(timetable, 1);
        double interest2 = installmentCalculator.calculateInterest(timetable, 2);
        double interest3 = installmentCalculator.calculateInterest(timetable, 7);
        double interest4 = installmentCalculator.calculateInterest(timetable, 20);
        Assertions.assertEquals(20d, interest1);
        Assertions.assertEquals(19d, interest2);
        Assertions.assertEquals(14d, interest3);
        Assertions.assertEquals(1d, interest4);
    }

    @Test
    public void testCalculateInterestForUnknownType() {
        Timetable timetable = new Timetable(1, 100, 10, 0.1, 10, "unknown");
        double interest1 = installmentCalculator.calculateInterest(timetable, 1);
        double interest2 = installmentCalculator.calculateInterest(timetable, 9);
        Assertions.assertEquals(0, interest1);
        Assertions.assertEquals(0, interest2);
    }

}
