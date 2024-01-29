package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.List;

public class TimingObservations {

    public static void main(String[] args) {
        int[] arrayOfNValues = {50, 100, 400, 400, 800}; // Adjust as needed

        List<Long> quadrithmicTimes = new ArrayList<>();
        List<Long> quadraticTimes = new ArrayList<>();
        List<Long> quadraticWithCalipersTimes = new ArrayList<>();
        List<Long> cubicTimes = new ArrayList<>();

        for (int n : arrayOfNValues) {
            // Run Quadrithmic algorithm
            long quadrithmicTime = measureTime(() -> runQuadrithmicAlgorithm(n));
            quadrithmicTimes.add(quadrithmicTime);

            // Run Quadratic algorithm
            long quadraticTime = measureTime(() -> runQuadraticAlgorithm(n));
            quadraticTimes.add(quadraticTime);

            // Run QuadraticWithCalipers algorithm
            long quadraticWithCalipersTime = measureTime(() -> runQuadraticWithCalipersAlgorithm(n));
            quadraticWithCalipersTimes.add(quadraticWithCalipersTime);

            // Run Cubic algorithm
            long cubicTime = measureTime(() -> runCubicAlgorithm(n));
            cubicTimes.add(cubicTime);
        }

        // Print or store timing data as needed
        System.out.println("Quadrithmic Times: " + quadrithmicTimes);
        System.out.println("Quadratic Times: " + quadraticTimes);
        System.out.println("QuadraticWithCalipers Times: " + quadraticWithCalipersTimes);
        System.out.println("Cubic Times: " + cubicTimes);
    }

    private static long measureTime(Runnable runnable) {
        long startTime = System.nanoTime();
        runnable.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static void runQuadrithmicAlgorithm(int n) {
        // Instantiate and run the ThreeSumQuadrithmic algorithm with input size N
        ThreeSumQuadrithmic algorithm = new ThreeSumQuadrithmic(generateInputArray(n));
        algorithm.getTriples();
    }

    private static void runQuadraticAlgorithm(int n) {
        // Instantiate and run the ThreeSumQuadratic algorithm with input size N
        ThreeSumQuadratic algorithm = new ThreeSumQuadratic(generateInputArray(n));
        algorithm.getTriples();
    }

    private static void runQuadraticWithCalipersAlgorithm(int n) {
        // Instantiate and run the ThreeSumQuadraticWithCalipers algorithm with input size N
        ThreeSumQuadraticWithCalipers algorithm = new ThreeSumQuadraticWithCalipers(generateInputArray(n));
        algorithm.getTriples();
    }

    private static void runCubicAlgorithm(int n) {
        // Instantiate and run the ThreeSumCubic algorithm with input size N
        ThreeSumCubic algorithm = new ThreeSumCubic(generateInputArray(n));
        algorithm.getTriples();
    }

    private static int[] generateInputArray(int n) {
        // Implement your logic to generate an input array of size N
        // This can be randomized or follow a specific pattern based on your testing requirements
        return new int[n];
    }
}

