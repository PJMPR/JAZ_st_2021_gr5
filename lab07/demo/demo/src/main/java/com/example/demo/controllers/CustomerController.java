package com.example.demo.controllers;

import com.example.demo.repositories.CustomerRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("customers")
public class CustomerController {

    CustomerRepository repository;

    @Autowired
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "ranking/bySpendMoney", method = GET)
    public ResponseEntity get10ClientsThatSpentMost() {
        return ResponseEntity.ok(repository.findTop10BySpentMost());
    }

    @RequestMapping(value = "ranking/bySpendMoney.jpg", params = "chart", method = GET)
    public void get10ClientsThatSpentMostChart(@RequestParam String chart, HttpServletResponse response) throws IOException {

        final PieDataset pieDataset = build10ClientsThatSpentMostChart();
        final String title = "Top 10 Clients That Spent Most";

        final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("id:{0} = {1}zl");

        final JFreeChart pieChart3D = ChartFactory.createPieChart3D(title, pieDataset, true, true, Locale.getDefault());
        final PiePlot3D piePlot3D = (PiePlot3D) pieChart3D.getPlot();
        piePlot3D.setLabelGenerator(labelGenerator);

        writeChartAsJPGImage(pieChart3D, 700, 400, response);
    }

    @RequestMapping(value = "ranking/byWatchedMovies", method = GET)
    public ResponseEntity get10ClientsThatWatchedMost() {
        return ResponseEntity.ok(repository.findTop10ByWatchedMost());
    }

    @RequestMapping(value = "ranking/byWatchedMovies.jpg", params = "chart", method = GET)
    public void get10ClientsThatWatchedMostChart(@RequestParam String chart, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = build10ClientsThatWatchedMostChart();
        final String title = "10 clients that watched most";
        final String categoryAxisLabel = "Clients";
        final String valueAxisLabel = "Number of films";

        final JFreeChart barChart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, categoryDataset, PlotOrientation.VERTICAL, true, true, true);
        final CategoryPlot categoryPlot = (CategoryPlot) barChart.getPlot();
        final CategoryItemRenderer categoryItemRenderer = categoryPlot.getRenderer();
        categoryItemRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        categoryItemRenderer.setDefaultItemLabelsVisible(true);

        final ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
        categoryItemRenderer.setDefaultPositiveItemLabelPosition(position);

        writeChartAsJPGImage(barChart, 900, 400, response);
    }

    @RequestMapping(value = "activity/rentMoviesByMonth", params = "year", method = GET)
    public ResponseEntity getRentMoviesByMonth(@RequestParam String year) {
        return ResponseEntity.ok(repository.findRentMoviesByMonth(year));
    }

    @RequestMapping(value = "activity/rentMoviesByMonth.jpg", params = {"year", "chart"}, method = GET)
    public void getRentMoviesByMonthChart(@RequestParam String year, @RequestParam String chart, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = buildRentMoviesByMonthChart(year);
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

        writeChartAsJPGImage(barChart, 900, 400, response);
    }

    @RequestMapping(value = "activity/rentMoviesByMonth", params = "customerid", method = GET)
    public ResponseEntity getRentMoviesByMonthByCustomer(@RequestParam String customerid) {
        return ResponseEntity.ok(repository.findRentMoviesByMonthByCustomer(customerid));
    }

    @RequestMapping(value = "activity/rentMoviesByMonth.jpg", params = {"customerid", "chart"}, method = GET)
    public void getRentMoviesByMonthByCustomerChart(@RequestParam String customerid, @RequestParam String chart, HttpServletResponse response) throws IOException {
        final DefaultCategoryDataset categoryDataset = buildRentMoviesByMonthByCustomerChart(customerid);
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

        writeChartAsJPGImage(barChart, 900, 400, response);
    }

    public void writeChartAsJPGImage(final JFreeChart chart, final int width, final int height, HttpServletResponse response) throws IOException {
        final BufferedImage bufferedImage = chart.createBufferedImage(width, height);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), bufferedImage);
    }

    public PieDataset build10ClientsThatSpentMostChart() {
        final DefaultPieDataset pieDataset = new DefaultPieDataset();
        repository.findTop10BySpentMost().forEach((data) -> pieDataset.setValue(data.getCustomer().getId(), data.getSpent()));

        return pieDataset;
    }

    private DefaultCategoryDataset build10ClientsThatWatchedMostChart() {
        final Comparable<String> rowKey = "Customer ID";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        repository.findTop10ByWatchedMost().forEach((data) -> categoryDataset.setValue(data.getWatched(), rowKey, String.valueOf(data.getCustomer().getId())));

        return categoryDataset;
    }

    private DefaultCategoryDataset buildRentMoviesByMonthChart(String year) {
        final Comparable<String> rowKey = "Month";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        repository.findRentMoviesByMonth(year).forEach((data) -> categoryDataset.setValue(data.getRentmovies(), rowKey, String.valueOf(data.getMonth())));

        return categoryDataset;
    }

    private DefaultCategoryDataset buildRentMoviesByMonthByCustomerChart(String id) {
        final Comparable<String> rowKey = "Month";
        final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        repository.findRentMoviesByMonthByCustomer(id).forEach((data) -> categoryDataset.setValue(data.getRentmovies(), rowKey, String.valueOf(data.getMonth())));

        return categoryDataset;
    }
}
