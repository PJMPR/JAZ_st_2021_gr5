package com.pjwstk.sakila.logic.charts;

import com.pjwstk.sakila.logic.charts.data.Series;

import java.io.IOException;

public interface ChartCreation {
    ChartCreator makePieChart(Series series);

    ChartCreator makeBarChart(Series series);

    ChartCreator makeLinearChart(Series series);

    byte[] toByteArray();

    void toFile(String filePath);
}
