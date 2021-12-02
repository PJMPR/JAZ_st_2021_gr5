package com.example.demo.charts;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;

import java.io.IOException;

public interface IChartGenerator {
    byte[] generate(String title,String type,String xAxisLabel,String yAxisLabel ) throws IOException;
    AbstractDataset getDataset();
}
