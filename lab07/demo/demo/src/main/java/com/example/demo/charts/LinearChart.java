package com.example.demo.charts;

import com.example.demo.databuilders.WriteAsJPG;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LinearChart extends Chart {

    public LinearChart(HttpServletResponse response, DefaultCategoryDataset categoryDataset) {
        super(response, categoryDataset);
    }

    public void linearChart() throws IOException {
        final String title = "Income by month";
        final String categoryAxisLabel = "Month";
        final String valueAxisLabel = "Income";

        final JFreeChart lineChart = ChartFactory.createLineChart(title, categoryAxisLabel, valueAxisLabel, categoryDataset, PlotOrientation.VERTICAL, true, true, true);
        final CategoryPlot categoryPlot = (CategoryPlot) lineChart.getPlot();
        final CategoryItemRenderer categoryItemRenderer = categoryPlot.getRenderer();
        categoryItemRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        categoryItemRenderer.setDefaultItemLabelsVisible(true);

        final ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_LEFT);
        categoryItemRenderer.setDefaultPositiveItemLabelPosition(position);

        new WriteAsJPG(lineChart, 900, 400, response).writeChartAsJPGImage();
    }
}
