package com.example.demo.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class PieChartGenerator {
    private final DefaultPieDataset dataset = new DefaultPieDataset();

    public PieChartGenerator() {
    }

    public DefaultPieDataset getDataset() {
        return dataset;
    }

    public DefaultPieDataset setDataset() {
        return dataset;
    }

    public byte[] generate(String title) throws IOException {
        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false);


        File pieChart = new File("Pie_Chart" + title + ".jpeg");
        ChartUtilities.saveChartAsJPEG(pieChart, chart, 560, 370);
        BufferedImage bImage = ImageIO.read(new File("Pie_Chart"+title+".jpeg"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        return bos.toByteArray();
    }
}
