package com.pjwstk.sakila.logic.charts.builder;

import com.pjwstk.sakila.logic.charts.data.Series;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.function.Supplier;

public class ChartBuilder {
    private JFreeChart chart;
    private DefaultCategoryDataset dataset;
    private String chartTitle;
    private Supplier<JFreeChart> factoryMethod;

    public ChartBuilder forSeries(Series series){
        dataset = new DefaultCategoryDataset();
        series.getValues().stream().forEach(x->dataset.addValue(x.getValue(), series.getTitle(), x.getName()));
        chartTitle = series.getTitle();
        return this;
    }

    public ChartBuilder forChartType(ChartType chartType) {

        factoryMethod = chartType
                .getFactoryMethod(chartTitle, dataset);
        return this;
    }

    public ChartBuilder build(){
        chart = factoryMethod.get();
        return this;
    }

    public JFreeChart getChart(){return chart;}


}
