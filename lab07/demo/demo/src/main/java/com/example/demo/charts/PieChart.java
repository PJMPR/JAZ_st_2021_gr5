package com.example.demo.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class PieChart {
    private final DefaultPieDataset dataset = new DefaultPieDataset();

    public PieChart() {
    }

    public DefaultPieDataset getDataset() {
        return dataset;
    }

    public byte[] generate(String title) throws IOException {
        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false);


        File pieChart = new File(title+"_pie_chart.jpg");
        ChartUtils.saveChartAsJPEG(pieChart, chart, 500, 350);
        BufferedImage bImage = ImageIO.read(new File(title+"_pie_chart.jpg"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        return bos.toByteArray();
    }
}