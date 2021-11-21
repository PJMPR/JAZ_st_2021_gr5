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
    }

    @Test
    public void testCalculateInterestForTypeConstant() {
        Timetable timetable = new Timetable(1, 100, 5, 0.1, 20, "constant");
        double interest = installmentCalculator.calculateInterest(timetable, 1);
        Assertions.assertEquals(2d, interest);
    }

    @Test
    public void testCalculateInterestForTypeDecreasing() {
        Timetable timetable = new Timetable(1, 200, 20, 0.1, 10, "decreasing");
        double interest = installmentCalculator.calculateInterest(timetable, 1);
        Assertions.assertEquals(20d, interest);
    }

    @Test
    public void testCalculateInterestForUnknownType() {
        Timetable timetable = new Timetable(1, 100, 10, 0.1, 10, "unknown");
        double interest = installmentCalculator.calculateInterest(timetable, 1);
        Assertions.assertEquals(0, interest);
    }

}
