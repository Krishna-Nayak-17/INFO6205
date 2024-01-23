package edu.neu.coe.info6205.randomwalk;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class RandomWalkGraph {

    public static void main(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Syntax: RandomWalkGraph steps [experiments]");
        int m = Integer.parseInt(args[0]);
        int n = 30;
        if (args.length > 1) n = Integer.parseInt(args[1]);

        // Create dataset
        XYSeries series = new XYSeries("Mean Distance vs Steps");

        for (int steps = 10; steps <= m; steps += 10) {
            double meanDistance = RandomWalk.randomWalkMulti(steps, n);
            series.add(steps, Math.sqrt(steps)); // Assuming d = sqrt(m)
        }

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Mean Distance vs Steps",
                "Steps",
                "Mean Distance",
                new XYSeriesCollection(series),
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Display the chart
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Random Walk Chart");
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ChartPanel(chart), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
