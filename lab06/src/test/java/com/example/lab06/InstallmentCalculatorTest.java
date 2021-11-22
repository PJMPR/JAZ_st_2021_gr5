package com.example.lab06;

import com.example.lab06.Type.InstallmentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstallmentCalculatorTest {
    InstallmentCalculator calculator = new InstallmentCalculator();

    @Test
    void InstallmentCalculatorReturnedArraySizeShouldBeEqualToCount(){
        assertEquals(5, calculator.calculate(new Timetable(1000, 5, InstallmentType.CONSTANT, 0.3, 30)).size());
        assertEquals(10, calculator.calculate(new Timetable(1000, 10, InstallmentType.CONSTANT, 0.3, 30)).size());
        assertEquals(7, calculator.calculate(new Timetable(1000, 7, InstallmentType.CONSTANT, 0.3, 30)).size());
        assertEquals(52, calculator.calculate(new Timetable(1000, 52, InstallmentType.CONSTANT, 0.3, 30)).size());
        assertEquals(95, calculator.calculate(new Timetable(1000, 95, InstallmentType.CONSTANT, 0.3, 30)).size());
    }

    @Test
    void InstallmentCalculatorShouldReturnCorrectCalculatedValuesForConstant(){
        ArrayList<Installment> list = calculator.calculate(new Timetable(100000, 36, InstallmentType.CONSTANT, 0.18, 0));
        for (int i = 0; i < 36; i++) {
            assertEquals(i+1, list.get(i).number());
            assertEquals(500, list.get(i).interest());
            assertEquals(0, list.get(i).fixedFee());
            assertEquals(3278, Math.round(list.get(i).amount()));
        }
        assertEquals(0, list.get(0).capital());
        assertEquals(118000, list.get(0).capitalToPay());
        assertEquals(3278, Math.round(list.get(35).capitalToPay()));
        assertEquals(118000, Math.round(list.get(35).capital() + list.get(35).amount()));
    }

    @Test
    void InstallmentCalculatorShouldReturnCorrectCalculatedValuesForDecreasing(){
        ArrayList<Installment> list = calculator.calculate(new Timetable(100000, 36, InstallmentType.DECREASING, 0.005, 0));
        for (int i = 0; i < 36; i++) {
            assertEquals(i+1, list.get(i).number());
            assertEquals(0, list.get(i).fixedFee());
        }
        assertEquals(0, list.get(0).capital());
        assertEquals(100000, list.get(0).capitalToPay());
        assertEquals(500, list.get(0).interest());
        assertEquals(2542, Math.round(list.get(0).amount()));

        assertEquals(26002, Math.round(list.get(10).capital()));
        assertEquals(73998, Math.round(list.get(10).capitalToPay()));
        assertEquals(370, Math.round(list.get(10).interest()));
        assertEquals(2672, Math.round(list.get(10).amount()));

        assertEquals(53333, Math.round(list.get(20).capital()));
        assertEquals(46667, Math.round(list.get(20).capitalToPay()));
        assertEquals(233, Math.round(list.get(20).interest()));
        assertEquals(2809, Math.round(list.get(20).amount()));

        assertEquals(96973, Math.round(list.get(35).capital()));
        assertEquals(3027, Math.round(list.get(35).capitalToPay()));
        assertEquals(15, Math.round(list.get(35).interest()));
        assertEquals(3027, Math.round(list.get(35).amount()));
    }
}
