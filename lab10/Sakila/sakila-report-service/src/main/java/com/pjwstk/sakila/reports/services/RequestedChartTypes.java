package com.pjwstk.sakila.reports.services;

import com.pjwstk.sakila.logic.charts.ChartCreation;
import com.pjwstk.sakila.logic.charts.data.Series;

import java.util.function.BiFunction;

public enum RequestedChartTypes {
    BAR((c,s)-> c.makeBarChart(s).toByteArray()),
    PIE((c,s)-> c.makePieChart(s).toByteArray()),
    LINEAR((c,s)-> c.makeLinearChart(s).toByteArray());

    BiFunction<ChartCreation,Series, byte[]> bytesSupplier;

    RequestedChartTypes(BiFunction<ChartCreation,Series, byte[]> bytesSupplier){
        this.bytesSupplier=bytesSupplier;
    }

    public byte[] getBytes(ChartCreation creator, Series series)
    {
        return bytesSupplier.apply(creator, series);
    }
}
