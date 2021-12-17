package com.pjwstk.sakila.logic;

import com.pjwstk.sakila.logic.charts.ChartCreation;
import com.pjwstk.sakila.logic.charts.data.Series;
import com.pjwstk.sakila.logic.charts.data.SeriesValue;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SakilaBusinessLogicApplication implements CommandLineRunner {

    private final ChartCreation creator;
    public static void main(String[] args) {
        SpringApplication.run(SakilaBusinessLogicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var series = new Series("test", List.of(
                new SeriesValue("test 1", 4),
                new SeriesValue("test 2", 3),
                new SeriesValue("test 3", 2),
                new SeriesValue("test 4", 5),
                new SeriesValue("test 5", 1)
        ));
        creator.makePieChart(series).toFile("test.jpg");
        creator.makePieChart(series).toByteArray();
        creator.makeBarChart(series).toFile("bar.jpg");
        creator.makeLinearChart(series).toFile("line.jpg");
    }
}
