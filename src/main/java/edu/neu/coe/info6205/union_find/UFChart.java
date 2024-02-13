package edu.neu.coe.info6205.union_find;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;

public class UFChart {

    public static void plot(int[] nValues, int[] mValues) {
        XYSeries series = new XYSeries("Connections");

        for (int i = 0; i < nValues.length; i++) {
            series.add(nValues[i], mValues[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "UF Analysis",
                "Number of Sites (n)",
                "Number of Connections (m)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Union-Find Analysis");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ChartPanel(chart));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        // Example usage
        int[] nValues = {10, 100, 1000, 10000}; // Example n values
        int[] mValues = new int[nValues.length]; // Store m values for each n

        for (int i = 0; i < nValues.length; i++) {
            mValues[i] = UFClient.count(nValues[i]); // Assume UFClient.count() method exists and returns int
        }

        plot(nValues, mValues);
    }
}
