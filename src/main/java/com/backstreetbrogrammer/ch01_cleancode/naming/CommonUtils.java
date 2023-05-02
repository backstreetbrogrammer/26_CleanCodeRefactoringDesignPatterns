package com.backstreetbrogrammer.ch01_cleancode.naming;

import java.time.Instant;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

public class CommonUtils {

    // number utils
    public static double getSquareRootPlusOne(final double num) {
        return Math.sqrt(num) + 1D;
    }

    public static int generateRandomNumberBetween(int low, int high) {
        return ThreadLocalRandom.current().nextInt(high - low) + low;
    }

    // String util
    public static String encode(final String data) {
        final StringBuilder builder = new StringBuilder();
        for (final char ch : data.toCharArray()) {
            builder.append(Character.isLetter(ch) ? ch ^ ' ' : ch);
        }
        return builder.reverse().toString();
    }

    // time util
    public static void printCurrentLocalTimeInUTC() {
        final String now = Instant.now().atZone(ZoneId.of("UTC")).toString();
        System.out.println(now);
    }

}
