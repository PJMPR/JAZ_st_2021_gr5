package com.lab06.demo;

import com.lab06.demo.creditcalculators.CreditConstant;
import com.lab06.demo.entities.Calculation;
import com.lab06.demo.entities.InstallmentType;
import com.lab06.demo.entities.Timetable;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreditConstantTest {
    @Test
    public void creditConstantShouldReturnProperValuesInList() {
        int amount = 300000;
        int installmentCount = 300;
        InstallmentType installmentType = InstallmentType.CONSTANT;
        double percentage = 0.047;
        int fixedRate = 30;

        Calculation calculation = new Calculation(amount, installmentCount, installmentType, percentage, fixedRate);
        List<Timetable> actual = new CreditConstant(calculation).constantRateCalculation();

        assertThat(actual.size(), is(300));
        assertThat(actual.get(299).getInterest(), is(703.35));
        assertThat(actual.get(299).getAmount(), is(1733.35));
    }
}
