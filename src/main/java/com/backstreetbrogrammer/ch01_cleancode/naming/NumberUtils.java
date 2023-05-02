package com.backstreetbrogrammer.ch01_cleancode.naming;

import java.util.concurrent.ThreadLocalRandom;

public class NumberUtils {

    public static double getSquareRootPlusOne(final double num) {
        return Math.sqrt(num) + 1D;
    }

    public static int generateRandomNumberBetween(int low, int high) {
        return ThreadLocalRandom.current().nextInt(high - low) + low;
    }
}
