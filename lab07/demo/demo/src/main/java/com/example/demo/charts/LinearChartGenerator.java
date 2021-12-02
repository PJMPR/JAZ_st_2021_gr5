package com.example.demo.charts;

import org.jfree.data.general.AbstractDataset;

import java.io.IOException;

public class LinearChartGenerator extends ChartGenerator implements IChartGenerator {

    @Override
    public byte[] generate(String title, String type, String xAxisLabel, String yAxisLabel) throws IOException {
        return new byte[0];
    }

    @Override
    public AbstractDataset getDataset() {
        return null;
    }
}
