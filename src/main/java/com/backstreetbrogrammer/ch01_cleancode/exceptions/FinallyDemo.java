package com.backstreetbrogrammer.ch01_cleancode.exceptions;

public class FinallyDemo {

    public static void main(String[] args) {
        try {
            Object result = null;
            System.out.println(result.toString()); // NullPointerException
        } finally {
            cleanup();
        }
    }

    private static void cleanup() {
        throw new IllegalStateException();
    }

}
