package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RentalControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getIncomeByMonthReturnsProperData() throws Exception {
        mvc.perform(get("/rental/incomeByMonth?year=2005"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))

                //first record
                .andExpect(jsonPath("$[0].month", is("5")))
                .andExpect(jsonPath("$[0].income", is(4824)))

                //last record
                .andExpect(jsonPath("$[3].month", is("8")))
                .andExpect(jsonPath("$[3].income", is(24072)));
    }

    @Test
    public void getIncomeFromToReturnsProperData() throws Exception {
        mvc.perform(get("/rental/income?from=2005-05-01&to=2005-08-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))

                //first record
                .andExpect(jsonPath("$[0].month", is("2005-05-01")))
                .andExpect(jsonPath("$[0].income", is(4824)))

                //last record
                .andExpect(jsonPath("$[2].month", is("2005-07-01")))
                .andExpect(jsonPath("$[2].income", is(28373)));
    }
}
