package com.example.demo.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class BarChartGenerator extends ChartGenerator implements IChartGenerator {
    AbstractDataset dataset = new DefaultCategoryDataset() {};

    public BarChartGenerator(){}

    public AbstractDataset getDataset(){
        return dataset;
    }

    public byte[] generate(String title,String type,String xAxisLabel,String yAxisLabel ) throws IOException {
        JFreeChart chart = ChartFactory.createBarChart(
                title,
                xAxisLabel,
                yAxisLabel,
                (CategoryDataset) dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );
        return saveChart(title,chart,type);
    }
}
