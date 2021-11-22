package com.example.LoanScheduler;

import com.example.LoanScheduler.Loan.Installment;
import com.example.LoanScheduler.Loan.Timetable;
import com.example.LoanScheduler.Loan.installmentType;
import com.example.LoanScheduler.calculator.InstallmentCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class LoanSchedulerApplicationTests {
	InstallmentCalculator calculator = new InstallmentCalculator();

	@Test
	public void timetableSHouldHave13Installments(){
		Timetable timetable = new Timetable(10000,13, installmentType.constant,0.01,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(13,installments.size());
	}

	@Test
	public void timetableSHouldHaveAllValuesEqualTo1(){
		Timetable timetable = new Timetable(1,1,installmentType.decreasing,1,1);
		Assertions.assertEquals(1,timetable.getAmount());
		Assertions.assertEquals(1,timetable.getInstallmentCount());
		Assertions.assertEquals(1,timetable.getFixedFee());
		Assertions.assertEquals(1,timetable.getPercentage());
	}

	@Test
	public void timetableSHouldHaveInstallmentTypeSetAsConstant(){
		Timetable timetable = new Timetable(1,1,installmentType.constant,1,1);
		Assertions.assertEquals(installmentType.constant,timetable.getInstallmentType());
	}

	@Test
	public void timetableSHouldHaveConstantInstallments(){
		Timetable timetable = new Timetable(1000,4,installmentType.constant,0,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(installmentType.constant,timetable.getInstallmentType());
		Assertions.assertEquals(250,installments.get(0).getAmount());
		Assertions.assertEquals(250,installments.get(1).getAmount());
		Assertions.assertEquals(250,installments.get(2).getAmount());
		Assertions.assertEquals(250,installments.get(3).getAmount());
	}

	@Test
	public void timetableSHouldHaveDecreasingInstallments(){
		Timetable timetable = new Timetable(1000,4,installmentType.decreasing,0.01,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(installmentType.decreasing,timetable.getInstallmentType());
		Assertions.assertTrue(installments.get(0).getAmount() > installments.get(1).getAmount());
		Assertions.assertTrue(installments.get(1).getAmount() > installments.get(2).getAmount());
		Assertions.assertTrue(installments.get(2).getAmount() > installments.get(3).getAmount());
	}

}
