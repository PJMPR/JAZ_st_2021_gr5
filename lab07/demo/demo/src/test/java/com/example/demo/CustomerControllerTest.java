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
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void get10ClientsThatSpentMostReturnsProperClients() throws Exception {
        mvc.perform(get("/customers/ranking/bySpendMoney"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))

                //first pearson
                .andExpect(jsonPath("$[0].customer.id", is(526)))
                .andExpect(jsonPath("$[0].customer.firstName", is("KARL")))
                .andExpect(jsonPath("$[0].customer.lastName", is("SEAL")))
                .andExpect(jsonPath("$[0].spent", is(221.55)))

                //last pearson
                .andExpect(jsonPath("$[9].customer.id", is(181)))
                .andExpect(jsonPath("$[9].customer.firstName", is("ANA")))
                .andExpect(jsonPath("$[9].customer.lastName", is("BRADLEY")))
                .andExpect(jsonPath("$[9].spent", is(174.66)));
    }

    @Test
    public void get10ClientsThatSpentMostChartProperClients() throws Exception {
        mvc.perform(get("/customers/ranking/byWatchedMovies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))

                //first pearson
                .andExpect(jsonPath("$[0].customer.id", is(148)))
                .andExpect(jsonPath("$[0].customer.firstName", is("ELEANOR")))
                .andExpect(jsonPath("$[0].customer.lastName", is("HUNT")))
                .andExpect(jsonPath("$[0].watched", is(46)))

                //last pearson
                .andExpect(jsonPath("$[9].customer.id", is(468)))
                .andExpect(jsonPath("$[9].customer.firstName", is("TIM")))
                .andExpect(jsonPath("$[9].customer.lastName", is("CARY")))
                .andExpect(jsonPath("$[9].watched", is(39)));
    }

    @Test
    public void get10ClientsThatWatchedMostProperData() throws Exception {
        mvc.perform(get("/customers/activity/rentMoviesByMonth?year=2005"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))

                //first record
                .andExpect(jsonPath("$[0].month", is("5")))
                .andExpect(jsonPath("$[0].rentmovies", is(1156)))

                //last record
                .andExpect(jsonPath("$[3].month", is("8")))
                .andExpect(jsonPath("$[3].rentmovies", is(5686)));
    }

    @Test
    public void getRentMoviesByMonthProperData() throws Exception {
        mvc.perform(get("/customers/activity/rentMoviesByMonth?customerid=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))

                //first record
                .andExpect(jsonPath("$[0].month", is("2005-05")))
                .andExpect(jsonPath("$[0].rentmovies", is(2)))

                //last record
                .andExpect(jsonPath("$[3].month", is("2005-08")))
                .andExpect(jsonPath("$[3].rentmovies", is(11)));
    }
}
