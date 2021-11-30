package com.example.demo.databuilders;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class WriteAsJPG {
    JFreeChart chart;
    int width;
    int height;
    HttpServletResponse response;

    public WriteAsJPG(JFreeChart chart, int width, int height, HttpServletResponse response) {
        this.chart = chart;
        this.width = width;
        this.height = height;
        this.response = response;
    }

    public void writeChartAsJPGImage() throws IOException {
        final BufferedImage bufferedImage = chart.createBufferedImage(width, height);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), bufferedImage);
    }


}
