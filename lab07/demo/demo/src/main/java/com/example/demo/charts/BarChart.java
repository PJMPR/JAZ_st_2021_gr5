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

public class BarChart extends Chart{
    public BarChart(HttpServletResponse response, DefaultCategoryDataset categoryDataset) {
        super(response, categoryDataset);
    }

    public void barChart() throws IOException {
        final String title = "Rent movies by month";
        final String categoryAxisLabel = "Months";
        final String valueAxisLabel = "Number of rented movies";

        final JFreeChart barChart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, categoryDataset, PlotOrientation.VERTICAL, true, true, true);
        final CategoryPlot categoryPlot = (CategoryPlot) barChart.getPlot();
        final CategoryItemRenderer categoryItemRenderer = categoryPlot.getRenderer();
        categoryItemRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        categoryItemRenderer.setDefaultItemLabelsVisible(true);

        final ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
        categoryItemRenderer.setDefaultPositiveItemLabelPosition(position);

        new WriteAsJPG(barChart, 900, 400, response).writeChartAsJPGImage();
    }
}
