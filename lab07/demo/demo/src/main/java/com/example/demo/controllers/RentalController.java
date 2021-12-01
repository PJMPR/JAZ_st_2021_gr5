package com.example.demo.controllers;

import com.example.demo.charts.LinearChart;
import com.example.demo.databuilders.rental.BuildIncome;
import com.example.demo.databuilders.rental.BuildIncomeByMonth;
import com.example.demo.projections.rental.IFindIncome;
import com.example.demo.repositories.RentalRepository;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("rental")
public class RentalController {
    RentalRepository repository;

    @Autowired
    public RentalController(RentalRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "incomeByMonth", params = "year", method = GET)
    public ResponseEntity<List<IFindIncome>> getIncomeByMonth(@RequestParam String year) {
        return new ResponseEntity<>(repository.incomeByMonth(year), HttpStatus.OK);
    }

    @RequestMapping(value = "incomeByMonth.jpg", params = {"chart", "year"}, method = GET)
    public void getIncomeByMonthChart(@RequestParam String chart, @RequestParam String year, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = new BuildIncomeByMonth(repository, year).buildIncomeByMonthChart();
        new LinearChart(response, categoryDataset).linearChart();
    }

    @RequestMapping(value = "income", params = {"from", "to"}, method = GET)
    public ResponseEntity<List<IFindIncome>> getIncomeFromTo(@RequestParam String from, @RequestParam String to) {
        return new ResponseEntity<>(repository.incomeByMonthFromTo(from, to), HttpStatus.OK);
    }

    @RequestMapping(value = "income.jpg", params = {"from", "to", "chart"}, method = GET)
    public void getIncomeFromToChart(@RequestParam String from, @RequestParam String to, @RequestParam String chart, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = new BuildIncome(repository, from, to).buildIncomeChart();
        new LinearChart(response, categoryDataset).linearChart();
    }
}
