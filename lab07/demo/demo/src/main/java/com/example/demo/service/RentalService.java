package com.example.demo.service;

import com.example.demo.charts.BarChartGenerator;
import com.example.demo.charts.IChartGenerator;
import com.example.demo.charts.LinearChartGenerator;
import com.example.demo.charts.PieChartGenerator;
import com.example.demo.data.Rental;
import com.example.demo.model.CustomerStats;
import com.example.demo.model.RentalStats;
import com.example.demo.repositories.RentalRepository;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class RentalService {
    public RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public RentalService() {
    }

    public int getIncomeInMonth(int year, int month) {
        Timestamp timeFrom = Timestamp.valueOf(year + "-" + month + "-01 00:00:01");
        Timestamp timeTo = Timestamp.valueOf(year + "-" + month + "-31 23:59:59");
        return rentalRepository.findAll().stream()
                .map(Rental::getPaymentsByRentalId)
                .map(x -> x.stream().filter(p -> p.getPaymentDate().after(timeFrom) && p.getPaymentDate().before(timeTo)))
                .map(x -> x.map(p -> p.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add).intValue())
                .reduce(0, Integer::sum);
    }

    public List<RentalStats> getIncomeByMonth(int year) {
        ArrayList<RentalStats> rentalStats = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        IntStream.rangeClosed(1, 12).forEach(i -> temp.add(getIncomeInMonth(year, i)));
        IntStream.rangeClosed(1, 12).forEach(i -> rentalStats.add(new RentalStats(i, (temp.get(i - 1)))));
        return rentalStats;
    }

    public byte[] generateRentalLinearChart(String title, String xAxis, String yAxis, List<RentalStats> entryData) throws IOException {
        LinearChartGenerator linearChartGenerator = new LinearChartGenerator();
        DefaultCategoryDataset dataset = (DefaultCategoryDataset) linearChartGenerator.getDataset();

        entryData.forEach(rentalStats -> dataset.setValue(
                (Number) rentalStats.getRentValue(),
                "",
                rentalStats.getMonth()
        ));

        return linearChartGenerator.generate(title, "linear", xAxis, yAxis);
    }

    public byte[] generateRentalBarChart(String title, String xAxis, String yAxis, List<RentalStats> entryData) throws IOException {
        BarChartGenerator barChartGenerator = new BarChartGenerator();
        DefaultCategoryDataset dataset = (DefaultCategoryDataset) barChartGenerator.getDataset();

        entryData.forEach(stats -> dataset.setValue(
                (Number) stats.getRentValue(),
                "income",
                stats.getMonth()
        ));

        return barChartGenerator.generate(title, "bar", xAxis, yAxis);
    }

    public byte[] generateRentalPieChart(String title, List<RentalStats> entryData) throws IOException {
        IChartGenerator pieChartGenerator = new PieChartGenerator();
        DefaultPieDataset dataset = (DefaultPieDataset) pieChartGenerator.getDataset();

        entryData.forEach(stats -> dataset.setValue(
                stats.getMonth(),
                (Number) stats.getRentValue())
        );

        return pieChartGenerator.generate(title, "pie", "", "");
    }
}
