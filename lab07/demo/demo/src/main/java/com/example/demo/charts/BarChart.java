package com.example.demo.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class BarChart {
    private final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    public BarChart(){}

    public DefaultCategoryDataset getDataset(){
        return dataset;
    }

    public byte[] generate(String title,String xAxisLabel,String yAxisLabel ) throws IOException {
        JFreeChart chart = ChartFactory.createBarChart(
                title,
                xAxisLabel,
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );

        File barChart = new File(title+"_bar_chart.jpg");
        ChartUtils.saveChartAsJPEG(barChart, chart, 500, 350);
        BufferedImage bImage = ImageIO.read(new File(title+"_bar_chart.jpg"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        return bos.toByteArray();
    }
}