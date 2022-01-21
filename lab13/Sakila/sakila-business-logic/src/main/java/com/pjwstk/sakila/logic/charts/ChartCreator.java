package com.pjwstk.sakila.logic.charts;

import com.pjwstk.sakila.logic.charts.builder.ChartBuilder;
import com.pjwstk.sakila.logic.charts.builder.ChartType;
import com.pjwstk.sakila.logic.charts.data.Series;
import com.pjwstk.sakila.logic.safe.SafeInvoking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

@Slf4j
@RequiredArgsConstructor
public class ChartCreator implements ChartCreation {

    private final ChartBuilder chartBuilder;
    private final SafeInvoking safe;
    JFreeChart chart;

    @Override
    public ChartCreator makePieChart(Series series){
        chart = getChart(series, ChartType.PIE);
        return this;
    }

    @Override
    public ChartCreator makeBarChart(Series series){
        chart = getChart(series, ChartType.BAR);
        return this;
    }
    @Override
    public ChartCreator makeLinearChart(Series series){
        chart = getChart(series, ChartType.LINEAR);
        return this;
    }

    private JFreeChart getChart(Series series, ChartType chartType) {
        return chartBuilder.forSeries(series)
                .forChartType(chartType)
                .build()
                .getChart();
    }

    @Override
    public byte[] toByteArray(){
        BufferedImage objBufferedImage=chart.createBufferedImage(600,800);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        safe.SafeInvoke(()-> ImageIO.write(objBufferedImage, "jpg", result))
                .onUnhandledException(e->log.error(e.getMessage(), e));
        return result.toByteArray();
    }

    @Override
    public void toFile(String filePath){
        File file = new File(filePath);
        safe.SafeInvoke(()-> ChartUtils.saveChartAsJPEG(file, chart,500,500))
                .onUnhandledException(e->log.error(e.getMessage(), e));
    }
}
