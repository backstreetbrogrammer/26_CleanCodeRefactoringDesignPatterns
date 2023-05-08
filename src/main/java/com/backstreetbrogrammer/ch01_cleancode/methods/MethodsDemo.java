package com.backstreetbrogrammer.ch01_cleancode.methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MethodsDemo {

    private int balance;

    public List<String> getSomeData() {
        try {
            // read from DB
        } catch (Exception e) {
            // operation failed
            // return null;
            return Collections.emptyList();
        }
        return new ArrayList<>();
    }

    public int withdraw(int amount) {
        if (amount > balance) {
            return -1;
        } else {
            balance -= amount;
            return 0;
        }
    }

    public void withdrawBetter(int amount) throws
            IllegalStateException {
        if (amount > balance) {
            throw new IllegalStateException();
        }
        balance -= amount;
    }

}
