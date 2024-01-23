/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    private void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    public double distance() {
        return Math.sqrt(x * x + y * y);
    }

    private void randomWalk(int m) {
        for (int i = 0; i < m; i++) {
            randomMove();
        }
    }

    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance += walk.distance();
        }
        return totalDistance / n;
    }

    public static void main(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
        int m = Integer.parseInt(args[0]);
        int n = 30;
        if (args.length > 1) n = Integer.parseInt(args[1]);

        System.out.println("Running experiments for " + m + " steps, " + n + " times...");
        System.out.println("Step\tMean Distance");

        for (int steps = 10; steps <= m; steps += 10) {
            double meanDistance = randomWalkMulti(steps, n);
            System.out.println(steps + "\t" + meanDistance);
        }
    }
}