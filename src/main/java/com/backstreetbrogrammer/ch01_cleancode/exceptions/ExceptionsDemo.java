package com.backstreetbrogrammer.ch01_cleancode.exceptions;

import java.io.*;

public class ExceptionsDemo {

    public static void main(String[] args) {
        final Mountain range[] = new Mountain[10];
        // Horrible abuse of exceptions. Don't ever do this!
        try {
            int i = 0;
            while (true) {
                range[i++].climb();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        for (Mountain m : range)
            m.climb();

        File file = new File("myFile.txt");
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            // read a file
        } catch (IOException e) {
            //log.error(e);
        }
    }

    private static class Mountain {
        public void climb() {

        }
    }
}
