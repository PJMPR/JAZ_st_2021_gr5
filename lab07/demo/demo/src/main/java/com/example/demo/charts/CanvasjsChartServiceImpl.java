package com.example.demo.charts;

        import java.util.List;
        import java.util.Map;

        import org.springframework.beans.factory.annotation.Autowired;

        import com.canvasjs.chart.daos.CanvasjsChartDao;

public class CanvasjsChartServiceImpl implements CanvasjsChartService {

    @Autowired
    private CanvasjsChartDao canvasjsChartDao;

    public void setCanvasjsChartDao(CanvasjsChartDao canvasjsChartDao) {
        this.canvasjsChartDao = canvasjsChartDao;
    }

    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartData() {
        return canvasjsChartDao.getCanvasjsChartData();
    }

}
