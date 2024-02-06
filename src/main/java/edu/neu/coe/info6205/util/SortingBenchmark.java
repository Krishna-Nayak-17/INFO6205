package edu.neu.coe.info6205.util;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class SortingBenchmark {
    public static void main(String[] args) {
        int[] ns = {10, 20, 40, 80, 160}; // Values of n to test
        int numberOfTrials = 10; // Number of trials for each n

        runBenchmark("Random Order", ns, numberOfTrials, SortingBenchmark::generateRandomArray);
        runBenchmark("Ordered", ns, numberOfTrials, SortingBenchmark::generateOrderedArray);
        runBenchmark("Partially Ordered", ns, numberOfTrials, SortingBenchmark::generatePartiallyOrderedArray);
        runBenchmark("Reverse Ordered", ns, numberOfTrials, SortingBenchmark::generateReverseOrderedArray);
    }

    private static void runBenchmark(String orderType, int[] ns, int numberOfTrials, Consumer<Integer[]> arrayGenerator) {
        System.out.println("Benchmark for " + orderType + " Arrays:");

        for (int n : ns) {
            long totalDuration = 0;

            for (int trial = 0; trial < numberOfTrials; trial++) {
                Integer[] array = generateArray(n, arrayGenerator);

                // Measure the time it takes to sort the array
                long startTime = System.nanoTime();
                // Use the sorting algorithm of your choice (e.g., MergeSort.sort)
                Arrays.sort(array);
                long endTime = System.nanoTime();

                totalDuration += (endTime - startTime);
            }

            // Calculate and print the average duration for sorting n elements
            long averageDuration = totalDuration / numberOfTrials;
            System.out.println("n = " + n + ", Average Duration: " + averageDuration + " nanoseconds");
        }

        System.out.println();
    }

    private static Integer[] generateArray(int n, Consumer<Integer[]> arrayGenerator) {
        Integer[] array = new Integer[n];
        arrayGenerator.accept(array);
        return array;
    }

    private static void generateRandomArray(Integer[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
    }

    private static void generateOrderedArray(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    private static void generatePartiallyOrderedArray(Integer[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = i + random.nextInt(100); // Allowing some randomness
        }
    }

    private static void generateReverseOrderedArray(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
    }
}
