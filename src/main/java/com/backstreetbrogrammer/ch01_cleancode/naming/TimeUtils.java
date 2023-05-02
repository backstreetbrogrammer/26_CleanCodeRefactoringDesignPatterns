package com.backstreetbrogrammer.ch01_cleancode.naming;

import java.time.Instant;
import java.time.ZoneId;

public class TimeUtils {

    public static void printCurrentLocalTimeInUTC() {
        final String now = Instant.now().atZone(ZoneId.of("UTC")).toString();
        System.out.println(now);
    }

}
