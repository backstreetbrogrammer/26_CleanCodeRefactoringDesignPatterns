package com.backstreetbrogrammer.ch01_cleancode.methods;

import java.util.*;

public class MethodsDemo {

    private int balance;

    public List<String> getSomeData() {
        try {
            // read from DB
        } catch (final Exception e) {
            // operation failed
            // return null;
            return Collections.emptyList();
        }
        return new ArrayList<>();
    }

    public int withdraw(final int amount) {
        if (amount > balance) {
            return -1;
        } else {
            balance -= amount;
            return 0;
        }
    }

    public void withdrawBetter(final int amount) throws
            IllegalStateException {
        if (amount > balance) {
            throw new IllegalStateException();
        }
        balance -= amount;
    }

    // Returns maximum value in collection - throws exception if empty
    public static <E extends Comparable<E>> E max(final Collection<E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("Empty collection");
        E result = null;
        for (final E e : c)
            if (result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);
        return result;
    }

    // Returns maximum value in collection as an Optional<E>
    public static <E extends Comparable<E>> Optional<E> max1(final Collection<E> c) {
        if (c.isEmpty())
            return Optional.empty();
        E result = null;
        for (final E e : c)
            if (result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);
        return Optional.of(result);
    }

}
