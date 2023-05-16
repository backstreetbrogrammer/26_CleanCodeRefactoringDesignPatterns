package com.backstreetbrogrammer.ch01_cleancode.exceptions;

import java.io.*;

public class ExceptionsDemo {

    public static void main(final String[] args) {
        final Mountain range[] = new Mountain[10];
        // Horrible abuse of exceptions. Don't ever do this!
        try {
            int i = 0;
            while (true) {
                range[i++].climb();
            }
        } catch (final ArrayIndexOutOfBoundsException e) {
        }

        for (final Mountain m : range)
            m.climb();

        final File file = new File("myFile.txt");
        try (final BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            // read a file
        } catch (final IOException e) {
            //log.error(e);
        }
    }

    private static class Mountain {
        public void climb() {

        }
    }
}
