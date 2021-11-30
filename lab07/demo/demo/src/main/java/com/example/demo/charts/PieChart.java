package com.example.demo.charts;

import com.example.demo.databuilders.WriteAsJPG;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.PieDataset;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class PieChart extends Chart{
    PieDataset pieDataset;

    public PieChart(HttpServletResponse response, PieDataset pieDataset) {
        super(response);
        this.pieDataset = pieDataset;
    }

    public void pieChart() throws IOException {
        final String title = "Top 10 Clients That Spent Most";

        final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("id:{0} = {1}zl");

        final JFreeChart pieChart3D = ChartFactory.createPieChart3D(title, pieDataset, true, true, Locale.getDefault());
        final PiePlot3D piePlot3D = (PiePlot3D) pieChart3D.getPlot();
        piePlot3D.setLabelGenerator(labelGenerator);

        new WriteAsJPG(pieChart3D, 700, 400, response).writeChartAsJPGImage();
    }
}
