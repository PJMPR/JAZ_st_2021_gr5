package com.example.demo;

import com.example.demo.services.RentalService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalServiceTest {
    @Autowired
    RentalService rentalService;

    @Test
    public void getIncomeByYearReturnsInt(){
        assertInstanceOf(int.class, rentalService.getIncomeByYear(2005, 1));
    }

    @Test
    public void getIncomeByYearReturnProperValues(){
        assertAll(
                () -> assertEquals(0, rentalService.getIncomeByYear(2005, 4)),
                () -> assertEquals(1156, rentalService.getIncomeByYear(2005, 5)),
                () -> assertEquals(2311, rentalService.getIncomeByYear(2005, 6)),
                () -> assertEquals(6709, rentalService.getIncomeByYear(2005, 7)),
                () -> assertEquals(5686, rentalService.getIncomeByYear(2005, 8)),
                () -> assertEquals(0, rentalService.getIncomeByYear(2005, 9))
        );
    }

}
