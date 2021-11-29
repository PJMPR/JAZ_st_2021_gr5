package com.example.demo.controllers;

import com.example.demo.repositories.RentalRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("rental")
public class RentalController {

    RentalRepository repository;

    @Autowired
    public RentalController(RentalRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "incomeByMonth", params = "year", method = GET)
    public ResponseEntity getIncomeByMonth(@RequestParam String year) {
        return ResponseEntity.ok(repository.incomeByMonth(year));
    }

    @RequestMapping(value = "incomeByMonth.jpg", params = {"chart", "year"}, method = GET)
    public void getIncomeByMonthChart(@RequestParam String chart, @RequestParam String year, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = buildIncomeByMonthChart(year);
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

        writeChartAsJPGImage(lineChart, 900, 400, response);
    }

    @RequestMapping(value = "income", params = {"from", "to"}, method = GET)
    public ResponseEntity getIncomeFromTo(@RequestParam String from, @RequestParam String to) {
        return ResponseEntity.ok(repository.incomeByMonthFromTo(from, to));
    }

    @RequestMapping(value = "income.jpg", params = {"from", "to", "chart"}, method = GET)
    public void getIncomeFromToChart(@RequestParam String from, @RequestParam String to, @RequestParam String chart, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = buildIncomeChart(from, to);
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

        writeChartAsJPGImage(lineChart, 900, 400, response);
    }

    public void writeChartAsJPGImage(final JFreeChart chart, final int width, final int height, HttpServletResponse response) throws IOException {
        final BufferedImage bufferedImage = chart.createBufferedImage(width, height);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), bufferedImage);
    }

    private DefaultCategoryDataset buildIncomeByMonthChart(String year) {
        final Comparable<String> rowKey = "Month";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        repository.incomeByMonth(year).forEach(data -> categoryDataset.setValue(data.getIncome(), rowKey, data.getMonth()));

        return categoryDataset;
    }

    private DefaultCategoryDataset buildIncomeChart(String from, String to) {
        final Comparable<String> rowKey = "Month";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        repository.incomeByMonthFromTo(from, to).forEach(data -> categoryDataset.setValue(data.getIncome(), rowKey, data.getMonth()));

        return categoryDataset;
    }
}
