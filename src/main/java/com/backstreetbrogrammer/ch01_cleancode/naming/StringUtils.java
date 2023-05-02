package com.backstreetbrogrammer.ch01_cleancode.naming;

public class StringUtils {

    public static String encode(final String data) {
        final StringBuilder builder = new StringBuilder();
        for (final char ch : data.toCharArray()) {
            builder.append(Character.isLetter(ch) ? ch ^ ' ' : ch);
        }
        return builder.reverse().toString();
    }

}
