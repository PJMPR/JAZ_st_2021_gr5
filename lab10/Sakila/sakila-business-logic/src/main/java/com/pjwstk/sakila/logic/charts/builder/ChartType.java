package com.pjwstk.sakila.logic.charts.builder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public enum ChartType {
    BAR((title,dataset)->()->ChartFactory.createBarChart(title,"","", dataset)),
    LINEAR((title,dataset)->()->ChartFactory.createLineChart(title,"","", dataset)),
    PIE((title,dataset)->()->ChartFactory.createMultiplePieChart(title, dataset, TableOrder.BY_ROW, false, false, false));

    private final BiFunction<String, DefaultCategoryDataset, Supplier<JFreeChart>> func;

    ChartType(BiFunction<String, DefaultCategoryDataset, Supplier<JFreeChart>> func ){
        this.func = func;
    }

    public Supplier<JFreeChart> getFactoryMethod(String chartTitle, DefaultCategoryDataset dataset){
        return func.apply(chartTitle, dataset);
    }


}

