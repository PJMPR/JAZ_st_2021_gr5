package com.canvasjs.chart.daos;

import java.util.List;
import java.util.Map;

import com.canvasjs.chart.data.CanvasjsChartData;

public class CanvasjsChartDaoImpl implements com.canvasjs.chart.daos.CanvasjsChartDao {

    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartData() {
        return CanvasjsChartData.getCanvasjsDataList();
    }

}