package com.example.demo.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class BarChartGenerator {
    private final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    public BarChartGenerator(){}

    public DefaultCategoryDataset getDataset(){
        return dataset;
    }

    public DefaultCategoryDataset setDataset(){
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

        File barChart = new File("Bar_Chart" + title + ".jpeg");
        ChartUtilities.saveChartAsJPEG(barChart, chart, 560, 370);
        BufferedImage bImage = ImageIO.read(new File("Bar_Chart"+title+".jpeg"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        return bos.toByteArray();
    }
}
