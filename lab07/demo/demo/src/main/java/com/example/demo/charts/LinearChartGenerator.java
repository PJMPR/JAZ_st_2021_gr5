package com.example.demo.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;

import java.io.IOException;

public class LinearChartGenerator extends ChartGenerator implements IChartGenerator {
    private final AbstractDataset dataset = new DefaultCategoryDataset(){};

    @Override
    public byte[] generate(String title, String type, String xAxisLabel, String yAxisLabel) throws IOException {
        JFreeChart chart = ChartFactory.createLineChart(
                title,
                xAxisLabel,
                yAxisLabel,
                (CategoryDataset) dataset,
                PlotOrientation.VERTICAL,
                true,true,false);
        return saveChart(title,chart,type);
    }

    @Override
    public AbstractDataset getDataset() {
        return dataset;
    }
}
