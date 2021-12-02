package com.example.demo.charts;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.AbstractDataset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public abstract class ChartGenerator {
    public byte[] saveChart(String title, JFreeChart chart, String type) throws IOException {
        File chartFile = new File(type + title + ".jpeg");
        ChartUtilities.saveChartAsJPEG(chartFile, chart, 560, 370);
        BufferedImage bImage = ImageIO.read(new File(type+title+".jpeg"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        return bos.toByteArray();
    }
}
